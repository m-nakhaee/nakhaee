package service;

import View.Main;
import data.dao.LogDao;
import data.dao.UserDao;
import data.entity.OperationLog;
import data.entity.User;
import enumPackage.OperationEnum;
import exception.LogOutException;
import exception.ReturnException;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ManagerPanel {
    private final UserDao userDao;
    private final Scanner scanner;

    public ManagerPanel(UserDao userDao, Scanner scanner) {
        this.userDao = userDao;
        this.scanner = scanner;
    }

    public void getCommand() throws LogOutException {
        while (true) {
            showManagerCommands();
            String inputCommand = scanner.nextLine();
            if (inputCommand.equals("3")) throw new LogOutException("good bye");
            try {
                if (inputCommand.equals("1")) getUserReport();
                if (inputCommand.equals("2")) getLogs();
            } catch (ReturnException e) {
                e.printStackTrace();
            }
        }
    }

    private void showManagerCommands() {
        System.out.println("enter proper number:");
        System.out.println("1- if you want to get a full report about users");
        System.out.println("2- if you want to get logs");
        System.out.println("3- log out");
    }

    private void getLogs() throws ReturnException, LogOutException {
        String userName = getUserName();
        Date[] dates = getStartEndDates();
        LogDao logDao = Main.context.getBean("logDao", LogDao.class);
        List<OperationLog> logs = logDao.getLogs(dates[0], dates[1], userName);
        showLogs(logs);
    }

    private String getUserName() throws ReturnException, LogOutException {
        String userName;
        while (true) {
            System.out.println("enter the user name you want to get his/her operation logs");
            showRetCommands();
            userName = scanner.nextLine();
            handleRetCommands(userName);
            User user = null;
            user = userDao.search(userName);
            if (user == null) System.out.println("this user name is not valid");
            else break;
        }
        return userName;
    }

    private void handleRetCommands(String userName) throws ReturnException, LogOutException {
        if (userName.equals("return")) throw new ReturnException("command canceled");
        if (userName.equals("log out")) throw new LogOutException("good bye");
    }

    private void showRetCommands() {
        System.out.println("enter 'return' to return");
        System.out.println("enter \'log out\' to log out");
    }

    private void showLogs(List<OperationLog> logs) {
        for (OperationLog log : logs) {
            OperationEnum operation = log.getOperation();
            if (operation.equals(OperationEnum.addToCart))
                System.out.println("the user: " + log.getAuthority()
                        + " added the product " + log.getProductId() + " to ther cart at "
                        + log.getTime() + " in " + log.getDate());
            if (operation.equals(OperationEnum.logIn))
                System.out.println("the user: " + log.getAuthority() + " logged in to the shop at "
                        + log.getTime() + " in " + log.getDate());
            if (operation.equals(OperationEnum.purchase))
                System.out.println("the user: " + log.getAuthority() + " purchased at "
                        + log.getTime() + " in " + log.getDate());
            if (operation.equals(OperationEnum.removeFromCart))
                System.out.println("the user: " + log.getAuthority()
                        + " removed the product " + log.getProductId() + " from the cart at "
                        + log.getTime() + " in " + log.getDate());
            if (operation.equals(OperationEnum.signUp))
                System.out.println("the user: " + log.getAuthority() + " signed up to the shop at "
                        + log.getTime() + " in " + log.getDate());
        }
    }

    private Date[] getStartEndDates() throws ReturnException, LogOutException {
        Date[] dates = new Date[2];
        while (true) {
            dates[0] = getDate("start");
            dates[1] = getDate("end");
            if (dates[1].before(dates[0]))
                System.out.println("invalid! end date should be after the start date");
            else return dates;
        }
    }

    private Date getDate(String startEndDate) throws ReturnException, LogOutException {
        System.out.println("enter " + startEndDate + " date like: YYYY-MM-DD");
        showRetCommands();
        String date;
        while (true) {
            date = scanner.nextLine();
            handleRetCommands(date);
            if (isDateFormatOk(date)) break;
            else System.out.println("invalid date format");
        }
        return Date.valueOf(date);
    }

    private boolean isDateFormatOk(String date) {
        String[] splitDate = date.split("-");
        if (splitDate.length != 3) return false;
        if (splitDate[0].length() != 4) return false;
        if (splitDate[1].length() != 2) return false;
        if (splitDate[2].length() != 2) return false;
        if (!UsefulMethods.isNumber(splitDate[0])) return false;
        if (!UsefulMethods.isNumber(splitDate[1])) return false;
        if (!UsefulMethods.isNumber(splitDate[2])) return false;
        return true;
    }

    private void getUserReport() {
        List<User> users = userDao.getAllUsers();
        Comparator<User> comparator = (a, b) -> a.getAge() <= b.getAge() ? -1 : 1;
        users.stream()
                .map(a -> (User) a)
                .sorted(comparator)
                .forEach(ManagerPanel::showReport);
    }

    private static void showReport(User user) {
        System.out.println("report about user: " + user.getUserName() + ":");
    }
}
