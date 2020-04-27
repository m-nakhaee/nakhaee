import dao.UserDao;
import dto.User;
import exception.StockNumberException;
import products.Electrical;
import products.Product;
import products.ReadableStuff;
import products.Shoe;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Store store = Store.getInstance();
    static Map<Electrical, Integer> electricalMap = store.getElectricalMap();
    static Map<ReadableStuff, Integer> readableMap = store.getReadableMap();
    static Map<Shoe, Integer> shoesMap = store.getShoesMap();
    static UserDao userDao = UserDao.getInstance();

    public static void main(String[] args) {
        String command = getEnterCommand();
        switch (command) {
            case "1": //TODO this is for current users
                break;
            case "2":
                User user = register();
        getOrder(user);
        }
    }

    private static String getEnterCommand() {
        System.out.println("WELCOME!");
        showProducts();
        System.out.println("1- i am a user and want to by something");
        System.out.println("2- i am new and want to register");
        return scanner.nextLine();
    }

    private static User register() {
        System.out.println("fill information to register:");
        String firstName = getFirstName();
        String lastName = getLastName();
        int phoneNumber = getPhoneNumber();
        String email = getEmail();
        String address = getAddress();
        User user = getUserNameAndPassword(firstName, lastName, phoneNumber, email, address);
        return user;
    }

    private static User getUserNameAndPassword(String firstName, String lastName,
                                               int phoneNumber, String email, String address) {
        if (regCanceled()) return null;
        String userName = getUserName();
        String password = getPassword();
        User user = new User(firstName, lastName, phoneNumber, email, address, userName, password);
        userDao.insertUser(user);
        return user;
    }

    private static boolean regCanceled() {
        while (true) {
            System.out.println("enter \"continue\" to register or \"exit\" to cancel registry");
            String inputString = scanner.nextLine();
            if (inputString.equals("continue")) break;
            if (inputString.equals("exit")) return true;
        }
        return false;
    }

    private static String getFirstName() {
        String firstName;
        do {
            System.out.println("1- first name");
            firstName = scanner.nextLine();
        } while (firstName.length() == 0);
        return firstName;
    }

    private static String getLastName() {
        String lastName;
        do {
            System.out.println("2- last name");
            lastName = scanner.nextLine();
        } while (lastName.length() == 0);
        return lastName;
    }

    private static String getUserName() {
        System.out.println("please enter a user name:");
        String username = scanner.nextLine();
        User availableUser = userDao.search(username);
        while (availableUser != null || username.length() == 0) {
            System.out.println("sorry! this user name is not valid!\nenter another user name pleas:");
            username = scanner.nextLine();
            availableUser = userDao.search(username);
        }
        return username;
    }

    private static String getPassword() {
        String password;
        do {
            System.out.println("enter a password");
            password = scanner.nextLine();
        } while (password.length() == 0);
        return password;
    }

    private static String getAddress() {
        String address;
        do { //TODO Check Address is Correct
            System.out.println("5- address");
            address = scanner.nextLine();
        } while (address.length() == 0);
        return address;
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
        String email;
        do { //TODO Check email is Correct
            System.out.println("4- email");
            email = scanner.nextLine();
        } while (email.length() == 0);
        return email;
    }

    private static boolean isNumber(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++)
            if (!Character.isDigit(input.charAt(i))) return false;
        return true;
    }

    private static void showProducts() {
        System.out.println("**************");
        System.out.println("1-ELECTRICAL PRODUCTS:");
        System.out.println(electricalMap);
        System.out.println("**************");
        System.out.println("2-SHOES:");
        System.out.println(shoesMap);
        System.out.println("**************");
        System.out.println("3-READABLE STUFFS:");
        System.out.println(readableMap);
    }

    private static void getOrder(User user) {
        showPurchasingCommands();
        while (true) {
            System.out.println("************");
            String command = scanner.nextLine();
            if (command.equals("add")) addProduct(user);
            if (command.equals("remove")) removeProduct(user);
            if (command.equals("show")) showCart(user);
            if (command.equals("cost")) PrintTotalCost(user);
            if (command.equals("continue"))
                if (finalizePurchase(user)) return;
            if (command.equals("exit")) return;
        }
    }

    private static boolean finalizePurchase(User user) {
        Map<Product, Integer> userOrder = user.getCart();
        Product product;
        int numberOfOrder;
        for (Map.Entry<Product, Integer> userOrderEntry : userOrder.entrySet()) {
            product = userOrderEntry.getKey();
            numberOfOrder = userOrderEntry.getValue();
            if (!checkStock(product, numberOfOrder)) return false;
        }
        // TODO this block should be synchronized
        store.updateStock(userOrder);
        user.purchase();
        showProducts(); //just for checking
    return true;
    }

    private static boolean checkStock(Product product, int numberOfOrder) {
        if (electricalMap.get(product) != null)
            if (!isNumberOfStockOK(product, numberOfOrder, electricalMap)) return false;
        if (readableMap.get(product) != null)
            if (!isNumberOfStockOK(product, numberOfOrder, readableMap)) return false;
        if (shoesMap.get(product) != null)
            if (!isNumberOfStockOK(product, numberOfOrder, shoesMap)) return false;
        return true;
    }

    private static boolean isNumberOfStockOK(Product product, int numberOfOrder, Map productMap) {
        int stockNumber = (int) productMap.get(product);
        if (stockNumber >= numberOfOrder) {
            return true;
        }
        try {
            throw new StockNumberException("** the stock number of " + product.getName()
                    + " is :" + stockNumber + "\n pleas remove the extras **");
        } catch (StockNumberException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private static void PrintTotalCost(User user) {
        System.out.println("total cost of your cart is: " + calculateCost(user.getCart()));
    }

    private static void showCart(User user) {
        System.out.println(user.getCart());
    }

    private static void removeProduct(User user) {
        String productStringCode;
        do {
            System.out.println("enter the product code you want to remove");
            productStringCode = scanner.nextLine();
        } while (!isNumber(productStringCode));
        int productCode = Integer.parseInt(productStringCode);
        Product product = store.getCodeProductMap().get(productCode);
        user.removeProduct(product);
    }

    private static void addProduct(User user) {
        String productStringCode;
        do {
            System.out.println("enter the product code you want to add");
            productStringCode = scanner.nextLine();
        } while (!isNumber(productStringCode));
        int productCode = Integer.parseInt(productStringCode);
        Product product = store.getCodeProductMap().get(productCode);
        user.addProduct(product);
    }

    private static void showPurchasingCommands() {
        System.out.println("\"add\" --> add a product to your cart");
        System.out.println("\"remove\" --> remove a product from to your cart");
        System.out.println("\"show\" --> show your cart");
        System.out.println("\"cost\" --> print total cost of your cart");
        System.out.println("\"continue\" --> finalize the purchase");
        System.out.println("\"exit\" --> exit the store");
    }

    private static double calculateCost(Map<Product, Integer> cart) {
        double cost = 0;
        int number;
        Product product;
        for (Map.Entry entry : cart.entrySet()) {
            number = (int) entry.getValue();
            product = (Product) entry.getKey();
            cost = cost + product.getPrice() * number;
        }
        return cost;
    }
}

