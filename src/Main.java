import dto.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {  //ask what exactly is the difference between static parameters and dynamics
    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> usernamePassMap = new HashMap<>();
    static Map<String, User> usernameUserMap = new HashMap<>();
    static Store store = Store.getInstance();

    public static void main(String[] args) {
        String userName = register();
        if (userName != null) {
            showProducts();
            getOrder();
        }
    }

    private static String register() {
        System.out.println("fill information to register:");
        String firstName = getFirstName();
        String lastName = getLastName();
        int phoneNumber = getPhoneNumber();
        String email = getEmail();
        String address = getAddress();
        String userName = finishRegister(firstName, lastName, phoneNumber, email, address);
        return userName;
    }

    private static String finishRegister(String firstName, String lastName,
                                         int phoneNumber, String email, String address) {
        if (regCanceled()) return null;
        User user = new User(firstName, lastName, phoneNumber, email, address);
        String userName = getUserName();
        String password = getPassword();
        usernamePassMap.put(userName, password);
        usernameUserMap.put(userName, user);
        return userName;
    }

    private static boolean regCanceled() {
        while (true) {
            System.out.println("enter \"finish\" to register or \"quit\" to cancel registry");
            String inputString = scanner.nextLine();
            if (inputString.equals("finish")) break;
            if (inputString.equals("quit")) return true;
        }
        return false;
    }

    private static String getFirstName() {
        String firstName = "";
        while (firstName.length() == 0) {
            System.out.println("1- first name");
            firstName = scanner.nextLine();
        }
        return firstName;
    }

    private static String getLastName() {
        String lastName = "";
        while (lastName.length() == 0) {
            System.out.println("2- last name");
            lastName = scanner.nextLine();
        }
        return lastName;
    }

    private static String getUserName() { //how can we throw and handle an exception without interrupting???
        System.out.println("please enter a user name:");
        String username = scanner.nextLine();
        String availablePass = usernamePassMap.get(username);
        while (availablePass != null || username.length() == 0) {
            System.out.println("sorry! this user name is not valid!\nenter another user name pleas:");
            username = scanner.nextLine();
            availablePass = usernamePassMap.get(username);
        }
        return username;
    }

    private static String getPassword() {
        String password = "";
        while (password.length() == 0) {
            System.out.println("enter a password");
            password = scanner.nextLine();
        }
        return password;
    }

    private static String getAddress() {
        System.out.println("5- address:");
        //TODO : address form
        return scanner.nextLine();
    }

    private static int getPhoneNumber() {
        while (true) {
            System.out.println("3- phone number:");
            String inputPhoneNumberString = scanner.nextLine();
            if (isNumber(inputPhoneNumberString)) {
                return Integer.parseInt(inputPhoneNumberString);
            }
        }
    }

    private static String getEmail() {
        System.out.println("4- email:");
        //TODO : check email validity
        return scanner.nextLine();
    }

    private static boolean isNumber(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++)
            if (!Character.isDigit(input.charAt(i))) return false;
        return true;
    }

    private static void showProducts() {
        System.out.println("**************");
        System.out.println("ELECTRICAL PRODUCTS:");
        System.out.println(store.getElectricalMap());
        System.out.println("**************");
        System.out.println("SHOES:");
        System.out.println(store.getShoesMap());
        System.out.println("**************");
        System.out.println("READABLE STUFFS:");
        System.out.println(store.getReadableMap());
    }

    private static void getOrder() {

    }
}
