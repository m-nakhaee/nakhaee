package service;

import View.Main;
import data.entity.User;
import enumPackage.OperationEnum;
import exception.ExitStoreException;
import exception.LogOutException;
import exception.ReturnException;

import java.util.Scanner;

public class StoreManager {

    private final Scanner scanner;

    public StoreManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void getStart() throws ExitStoreException {
        System.out.println("WELCOME!");
        while (true) {
            try {
                checkExit();
                User user = checkForSigningUp();
                user = checkForLoggingIn(user);
                handleManagerOrUserOrder(user);
            } catch (ReturnException| LogOutException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleManagerOrUserOrder(User user) throws LogOutException {
        if (user != null) {
            if (user.getUserName().equals("manager")) {
                ManagerPanel managerPanel = Main.context.getBean("managerPanel", ManagerPanel.class);
                managerPanel.getCommand();
            } else {
                GetOrderPanel getOrderPanel = Main.context.getBean("getOrderPanel", GetOrderPanel.class);
                getOrderPanel.getOrder(user.getUserName());
            }
        }
    }

    private User checkForLoggingIn(User user) throws ReturnException {
        if (user == null) {
            LogInPanel logInPanel = Main.context.getBean("logInPanel", LogInPanel.class);
            user = logInPanel.logeIn();
            UsefulMethods.recordLog(OperationEnum.logIn, user.getUserName());
        }
        return user;
    }

    private User checkForSigningUp() throws ReturnException {
        User user = null;
        System.out.println("if you want to sign up, pleas enter \'new'");
        System.out.println("if not, enter something else");
        String input = scanner.nextLine().trim();
        if (input.equals("new")) {
            SignUpPanel signUpPanel = Main.context.getBean("signUpPanel", SignUpPanel.class);
            user = signUpPanel.signUp();
            UsefulMethods.recordLog(OperationEnum.signUp, user.getUserName());
        }
        return user;
    }

    private void checkExit() throws ExitStoreException {
        String input = "";
        while (!input.equals("yes") && !input.equals("no")) {
            System.out.println("do you want to close the store? ('yes' or 'no')");
            input = scanner.nextLine();
        }
        if (input.equals("yes")) throw new ExitStoreException();
    }

}
