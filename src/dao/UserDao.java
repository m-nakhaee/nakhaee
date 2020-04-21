package dao;

import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static UserDao singleToneInstance = new UserDao();
    private String query;
    private Connection connection;
    private PreparedStatement preparedStatement;

    private UserDao() {
    }

    public static UserDao getInstance() {
        return singleToneInstance;
    }

    public void insertUser(User user) {
        try {
            connection = MyConnection.getConnection();
            executeUpdate(user);
            closePreparedStatementAndConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(User user) throws SQLException {
        query = "insert into user (first_name, last_name, phone_number, email, address, user_name, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setInt(3, user.getPhoneNumber());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setString(6, user.getUserName());
        preparedStatement.setString(7, user.getPassword());
        preparedStatement.executeUpdate();
    }

    private void closePreparedStatementAndConnection() throws SQLException {
        preparedStatement.close();
        connection.close();
    }

    public User search(String userName) {
        try {
            connection = MyConnection.getConnection();
            ResultSet resultSet = executeQuery(userName);
            User user = getUser(resultSet);
            closePreparedStatementAndConnection();
            System.out.println("im here");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()){
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setPhoneNumber(resultSet.getInt(4));
            user.setEmail(resultSet.getString(5));
            user.setAddress(resultSet.getString(6));
            user.setUserName(resultSet.getString(7));
            user.setPassword(resultSet.getString(8));
            return user;
        }
        return null;
    }

    private ResultSet executeQuery(String userName) throws SQLException {
        query = "SELECT* from shop_user WHERE user_name = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        return preparedStatement.executeQuery();
    }
}
