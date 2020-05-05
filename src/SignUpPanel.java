import dao.UserDao;
import dto.User;

public class SignUpPanel {
    private UserDao userDao = UserDao.getInstance();

    public void signUp() throws Exception {
        System.out.println("fill information to sign up:");
        System.out.println("----enter \"cancel\" any time you want to cancel registration----");
        String firstName = getFirstName();
        String lastName = getLastName();
        int phoneNumber = getPhoneNumber();
        String email = getEmail();
        String address = getAddress();
        String userName = getUserName();
        String password = getPassword(userName);
        User user = User.UserBuilder.aUser()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withAddress(address)
                .withUserName(userName)
                .withPassword(password)
                .build();
        userDao.insertUser(user);
        System.out.println(firstName + "! your registration completed!\n");
    }

    private String getFirstName() throws Exception {
        String inputString;
        do {
            System.out.println("1- first name");
            inputString = getString();
        } while (!isNameFormatCorrect(inputString));
        return inputString.trim();
    }

    private String getString() throws Exception {
        String inputString = StoreManager.scanner.nextLine();
        if (inputString.equals("cancel")) throw new Exception("registration canceled");
        return inputString.trim();
    }

    private boolean isNameFormatCorrect(String name) {
        name = name.replaceAll(" ", "");
        if (name.length() == 0) return false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(c >= 65 && c <= 90) &&
                    !(c >= 97 && c <= 122)) return false;
        }
        return true;
    }

    private String getLastName() throws Exception {
        String inputString;
        do {
            System.out.println("2- last name");
            inputString = getString();
        } while (!isNameFormatCorrect(inputString));
        return inputString.trim();
    }

    private String getUserName() throws Exception {
        String inputString;
        String trimInputString;
        User availableUser;
        do {
            System.out.println("please enter a user name:");
            inputString = getString();
            trimInputString = inputString.trim();
            availableUser = userDao.search(trimInputString);
            if (availableUser != null) {
                System.out.println("sorry! this user name is not valid!");
            }
        } while (availableUser != null || inputString.length() == 0);
        return inputString.trim();
    }

    private String getPassword(String userName) throws Exception {
        String inputString;
        do {
            System.out.println("enter a password (at least 6 chars)");
            inputString = getString();
            if (inputString.equals(userName))
                System.out.println("user name and password should be different");
        } while (inputString.equals(userName) || !isPasswordFormatOk(inputString));
        return inputString;
    }

    private boolean isPasswordFormatOk(String inputString) {
        if (inputString.length() < 6) return false;
        return true;
    }

    private String getAddress() throws Exception {
        String inputString;
        do {
            System.out.println("5- address");
            inputString = getString();
        } while (!isAddressFormatOk(inputString));
        return inputString.trim();
    }

    private boolean isAddressFormatOk(String inputString) {
        inputString = inputString.replaceAll(" ", "");
        if (inputString.length() < 10) return false;
        return true;
    }

    private int getPhoneNumber() throws Exception {
        String inputString;
        int phoneNumber = 0;
        do {
            System.out.println("3- phone number: (without area code && at least 4 digits)");
            inputString = getString();
            if (StoreManager.isNumber(inputString))
                phoneNumber = Integer.parseInt(inputString);
        } while (!isPhoneNumberFormatOk(phoneNumber));
        return phoneNumber;
    }

    private boolean isPhoneNumberFormatOk(int number) {
        String stringNumber = Integer.toString(number);
        if (stringNumber.length() != 4) return false;
        if (stringNumber.charAt(0) == '0') return false;
        return true;
    }

    private String getEmail() throws Exception {
        String inputString;
        do {
            System.out.println("4- email");
            inputString = getString();
        } while (!isEmailFormatOk(inputString));
        return inputString.trim();
    }

    private boolean isEmailFormatOk(String inputString) {
        inputString = inputString.replaceAll(" ", "");
        String[] email = inputString.split("@");
        if (email.length != 2) return false;
        int domainLength = email[1].length();
        if (domainLength < 5) return false;
        if (email[1].charAt(domainLength - 4) != '.') return false;
        return true;
    }

}
