package service;

import View.StoreManager;
import data.dao.UserDao;
import data.dto.User;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ManagerPanel {
    private UserDao userDao;
    private Scanner scanner = StoreManager.scanner;

    public ManagerPanel(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getCommand() throws SQLException {
        System.out.println("enter \"report\" if you want to report about users");
        System.out.println("otherwise enter any thing else");
        String inputCommand = scanner.nextLine();
        if (inputCommand.equals("report"))
            getReport();
    }

    private void getReport() throws SQLException {
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
