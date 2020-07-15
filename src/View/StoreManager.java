package View;

import data.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.GetOrderPanel;
import service.LogInPanel;
import service.ManagerPanel;
import service.SignUpPanel;
import service.exception.InvalidUserPassException;
import service.exception.ReturnException;

import java.sql.SQLException;
import java.util.Scanner;

public class StoreManager {
    public static Scanner scanner = new Scanner(System.in);
    public static ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

    public static void main(String[] args) {
        getStart();
    }

    private static void getStart() {
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
            ManagerPanel managerPanel = context.getBean("managerPanel", ManagerPanel.class);
            managerPanel.getCommand();
        } else {
            GetOrderPanel getOrderPanel = context.getBean("getOrderPanel", GetOrderPanel.class);
            getOrderPanel.getOrder(user.getUserName());
        }
    }

    private static User checkForLoggingIn(User user) throws InvalidUserPassException, ReturnException, SQLException {
        if (user == null) {
            LogInPanel logInPanel = context.getBean("logInPanel", LogInPanel.class);
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
            SignUpPanel signUpPanel = context.getBean("signUpPanel", SignUpPanel.class);
            user = signUpPanel.signUp();
        }
        return user;
    }

}

