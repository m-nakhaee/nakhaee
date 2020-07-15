package service;

import View.StoreManager;
import data.dao.UserDao;
import data.dto.User;
import service.exception.InvalidUserPassException;
import service.exception.ReturnException;

import java.sql.SQLException;
import java.util.Scanner;

public class LogInPanel {
    private UserDao userDao;
    private Scanner scanner = StoreManager.scanner;

    public LogInPanel(UserDao userDao) {
        this.userDao = userDao;
    }

    public User logeIn() throws InvalidUserPassException, ReturnException, SQLException {
        System.out.println("********welcome to log in panel!********");
        System.out.println("----enter \"exit\" any time you want to exit----");
        String userName = getInputString("enter your user name");
        String password = getInputString("enter your password");
        User user = userDao.search(userName);
        if (user == null) throw new InvalidUserPassException("the user name is not correct");
        if (!user.getPassword().equals(password)) throw new InvalidUserPassException("wrong password");
        return user;
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
