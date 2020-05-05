import java.util.Scanner;

public class StoreManager {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("WELCOME!");
        CheckForSigningUp();
        LogInPanel logInPanel = new LogInPanel();
        try {
            logInPanel.logeIn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void CheckForSigningUp() {
        System.out.println("if you want to sign up, pleas enter \'new'");
        String input = scanner.nextLine().trim();
        if (input.equals("new")) {
            SignUpPanel signUpPanel = new SignUpPanel();
            try {
                signUpPanel.signUp();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean isNumber(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++)
            if (!Character.isDigit(input.charAt(i))) return false;
        return true;
    }
}

