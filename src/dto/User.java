package dto;

import products.Product;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String address; //TODO : rewrite it by Address class
    private Map<Product, Integer> cart = new HashMap<>();
    private String userName;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, int phoneNumber, String email, String address, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void addProduct(Product product) {
        Integer number = cart.get(product);
        if (number != null)
            cart.put(product, number + 1);
        else if (product != null) cart.put(product, 1);
        System.out.println(cart);
    }

    public void removeProduct(Product product) {
        System.out.println(cart);
        Integer number = cart.get(product);
        if (number != null)
            if (number == 1) cart.remove(product);
            else cart.put(product, number - 1);
        System.out.println(cart);
    }

    public void purchase() {
        //TODO
        emptyCart();
        System.out.println("purchasing is completed");
        System.out.println("the cart is empty now!");
    }

    private void emptyCart() {
        for (int i = 0; i < cart.size(); i++)
            cart.remove(i);
    }
}
