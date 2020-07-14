package View;

import data.dao.UserDao;
import data.dto.User;
import service.exception.InvalidUserPassException;
import service.exception.ReturnException;
import service.GetOrderPanel;
import service.LogInPanel;
import service.ManagerPanel;
import service.SignUpPanel;

import java.sql.SQLException;
import java.util.Scanner;

public class StoreManager {
    static Scanner scanner = new Scanner(System.in);
    static UserDao userDao = new UserDao();

    public static void main(String[] args) {
        System.out.println("WELCOME!");
        try {
            User user = checkForSigningUp();
            user = checkForLoggingIn(user);
            if (user != null) {
                handleManagerOrUserOrder(user);
            }
        } catch (ReturnException | InvalidUserPassException |
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleManagerOrUserOrder(User user) throws SQLException {
        if (user.getUserName().equals("manager")) {
            ManagerPanel managerPanel = new ManagerPanel(userDao);
            managerPanel.getCommand();
        } else {
            GetOrderPanel getOrderPanel = new GetOrderPanel();
            getOrderPanel.getOrder(user.getUserName());
        }
    }

    private static User checkForLoggingIn(User user) throws InvalidUserPassException, ReturnException, SQLException {
        if (user == null) {
            LogInPanel logInPanel = new LogInPanel(userDao);
            user = logInPanel.logeIn();
        }
        return user;
    }

    private static User checkForSigningUp() throws ReturnException {
        User user = null;
        System.out.println("if you want to sign up, pleas enter \'new'");
        System.out.println("if not, enter something else");
        String input = scanner.nextLine().trim();
        if (input.equals("new")) {
            SignUpPanel signUpPanel = new SignUpPanel();
            user = signUpPanel.signUp();
        }
        return user;
    }

}

