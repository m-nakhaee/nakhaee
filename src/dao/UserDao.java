package dao;

import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    String query;
    Connection connection;

    public UserDao() {
        query = "create table user " +
                "(id int not null primary key auto_increment, " +
                "first_name varchar(10), " +
                "last_name varchar(10), " +
                "phone_number int, " +
                "email varchar(10), " +
                "address varchar(100);";
    }

    private void insertUser(User user){
        query = "insert into user (first_name, last_name, phone_number, email, address) " +
                "VALUES (?, ?, ?, ?, ?);";
         connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
