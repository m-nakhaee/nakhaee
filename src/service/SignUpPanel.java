package service;

import data.dao.UserDao;
import data.dto.User;
import service.exception.InvalidInputException;
import service.exception.ReturnException;

import java.sql.SQLException;
import java.util.Scanner;

public class SignUpPanel {
    private UserDao userDao = new UserDao();
    private Scanner scanner = new Scanner(System.in);

    public User signUp() throws ReturnException {
        System.out.println("fill information to sign up:");
        System.out.println("----enter \"cancel\" any time you want to cancel registration----");
        String firstName = getName("1- first name");
        String lastName = getName("2- last name");
        int age = getAge();
        long phoneNumber = getPhoneNumber();
        String email = getEmail();
        String address = getAddress();
        String userName = getUserName();
        String password = getPassword(userName);
        User user = User.UserBuilder.aUser()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAge(age)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withAddress(address)
                .withUserName(userName)
                .withPassword(password)
                .build();
        userDao.insertUser(user);
        System.out.println("congratulation " + firstName + '!');
        System.out.println("your registration completed!\n");
        return user;
    }

    private int getAge() throws ReturnException {
        String inputString;
        while (true) {
            System.out.println("6- age");
            inputString = getString();
            try {
                if (isTrueAge(inputString)) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(inputString);
    }

    private boolean isTrueAge(String inputString) throws InvalidInputException {
        if (!UsefulMethods.isNumber(inputString))
            throw new InvalidInputException("incorrect age");
        return true;
    }

    private String getString() throws ReturnException {
        String inputString = scanner.nextLine();
        String trimInputString = inputString.trim();
        if (trimInputString.equals("cancel")) throw new ReturnException("registration canceled");
        return trimInputString;
    }

    private boolean isNameFormatCorrect(String name) throws InvalidInputException {
        name = name.replaceAll(" ", "");
        if (name.length() == 0) throw new InvalidInputException("invalid! empty input");
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(c >= 65 && c <= 90) &&
                    !(c >= 97 && c <= 122)) throw new InvalidInputException("invalid! non alphabetic characters");
        }
        return true;
    }

    private String getName(String name) throws ReturnException {
        String inputString;
        while (true) {
            System.out.println(name);
            inputString = getString();
            try {
                if (isNameFormatCorrect(inputString)) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return inputString;
    }

    private String getUserName() throws ReturnException {
        String inputString;
        while (true) {
            System.out.println("please enter a user name:");
            inputString = getString();
            try {
                if (isUserNameOk(inputString)) break;
            } catch (InvalidInputException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return inputString.trim();
    }

    private boolean isUserNameOk(String inputString) throws InvalidInputException, SQLException {
        User availableUser;
        availableUser = userDao.search(inputString);
        if (inputString.length() == 0) throw new InvalidInputException("empty");
        if (availableUser != null) throw new InvalidInputException("this user name is not available");
        return true;
    }

    private String getPassword(String userName) throws ReturnException {
        String inputString;
        while (true) {
            System.out.println("enter a password (at least 6 chars)");
            inputString = getString();
            try {
                if (isPasswordFormatOk(inputString, userName)) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return inputString;
    }

    private boolean isPasswordFormatOk(String inputString, String userName) throws InvalidInputException {
        if (inputString.equals(userName)) throw new InvalidInputException("user name and password should be different");
        if (inputString.length() < 6) throw new InvalidInputException("too short");
        return true;
    }

    private String getAddress() throws ReturnException {
        String inputString;
        while (true) {
            System.out.println("5- address");
            inputString = getString();
            try {
                if (isAddressFormatOk(inputString)) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return inputString.trim();
    }

    private boolean isAddressFormatOk(String inputString) throws InvalidInputException {
        inputString = inputString.replaceAll(" ", "");
        if (inputString.length() < 10) throw new InvalidInputException("this address is too short");
        return true;
    }

    private long getPhoneNumber() throws ReturnException {
        String inputString;
        while (true) {
            System.out.println("3- mobile phone number:");
            inputString = getString();
            try {
                if (isPhoneNumberFormatOk(inputString)) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return Long.parseLong(inputString);
    }

    private boolean isPhoneNumberFormatOk(String inputString) throws InvalidInputException {
        if (!UsefulMethods.isNumber(inputString))
            throw new InvalidInputException("invalid! please enter just digits");
        if (inputString.length() != 11)
            throw new InvalidInputException("invalid! entered number dos not have 11 digits");
        if (inputString.charAt(0) != '0') throw new InvalidInputException("invalid mobile phone");
        return true;
    }

    private String getEmail() throws ReturnException {
        String inputString;
        while (true) {
            System.out.println("4- email");
            inputString = getString();
            try {
                if (isEmailFormatOk(inputString)) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return inputString.trim();
    }

    private boolean isEmailFormatOk(String inputString) throws InvalidInputException {
        inputString = inputString.replaceAll(" ", "");
        String[] email = inputString.split("@");
        if (email.length != 2) throw new InvalidInputException("invalid email format!");
        int domainLength = email[1].length();
        if (domainLength < 5) throw new InvalidInputException("invalid email format!");
        if (email[1].charAt(domainLength - 4) != '.') throw new InvalidInputException("invalid email format");
        return true;
    }


}
