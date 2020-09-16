package data.entity;

import javax.persistence.*;

@Entity(name = "shop_user")
public class User {
    @Id @GeneratedValue
    private int id;

    //TODO rewrite it by class Person
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private int age;
    private String address; //TODO : rewrite it by Address class

    //TODO rewrite it by class User
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "phone_number")
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
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

    @Column(name = "user_name")
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

    public static class UserBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private int age;
        private long phoneNumber;
        private String email;
        private String address; //TODO : rewrite it by Address class
        private String userName;
        private String password;

        public static UserBuilder aUser() {
            return new UserBuilder();
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withPhoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setEmail(email);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
