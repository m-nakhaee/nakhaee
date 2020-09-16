package service;

import data.dao.UserDao;
import data.entity.User;
import exception.InvalidUserPassException;
import exception.ReturnException;

import java.util.Scanner;

public class LogInPanel {
    private final UserDao userDao;
    private final Scanner scanner;

    public LogInPanel(UserDao userDao, Scanner scanner) {
        this.userDao = userDao;
        this.scanner = scanner;
    }

    public User logeIn() throws ReturnException {
        System.out.println("********welcome to log in panel!********");
        System.out.println("----enter \"exit\" any time you want to exit----");
        while (true) {
            String userName = getInputString("enter your user name");
            String password = getInputString("enter your password");
            User user;
            try {
                user = userDao.search(userName);
                if (user == null)
                    throw new InvalidUserPassException("the user name is not correct");
                if (!user.getPassword().equals(password))
                    throw new InvalidUserPassException("wrong password");
                return user;
            } catch (InvalidUserPassException e) {
                e.printStackTrace();
            }
        }
    }

    private String getInputString(String message) throws ReturnException {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
            if (input.equals("exit")) throw new ReturnException("good bye");
        } while (input.length() == 0);
        return input.trim();
    }
}
