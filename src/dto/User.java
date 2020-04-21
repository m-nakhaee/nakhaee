package dto;

import products.Product;

import java.math.BigInteger;

public class User {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String address; //TODO : rewrite it by Address class
    private Product[] productList;

    public User() {
    }

    public User(String firstName, String lastName, int phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
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

    private void addProduct(){

    }
    private void removeProduct(){

    }
    private void finalConfirm(){{
    }
    }
}
