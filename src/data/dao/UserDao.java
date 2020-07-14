package data.dao;

import data.dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends SharedDao {

    public void insertUser(User user) {
        try {
            connection = MyConnection.getConnection();
            insertUserIntoTable(user);
            closePreparedStatementAndConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User search(String userName) throws SQLException {
        connection = MyConnection.getConnection();
        query = "SELECT* from shop_user WHERE user_name = '" + userName + "'";
        getResultSetFromDB();
        User user = null;
        if (resultSet.next()) user = getUserFromResultSet();
        closePreparedStatementAndConnection();
        return user;
    }

    private void insertUserIntoTable(User user) throws SQLException {
        query = "insert into shop_user (first_name, last_name, phone_number, email, address, user_name, password, age) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setLong(3, user.getPhoneNumber());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setString(6, user.getUserName());
        preparedStatement.setString(7, user.getPassword());
        preparedStatement.setInt(8, user.getAge());
        preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        connection = MyConnection.getConnection();
        while (resultSet.next())
            users.add(getUserFromResultSet());
        closePreparedStatementAndConnection();
        return null;
    }

    private User getUserFromResultSet() throws SQLException {
        User user = new User();
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setPhoneNumber(resultSet.getLong(4));
        user.setEmail(resultSet.getString(5));
        user.setAddress(resultSet.getString(6));
        user.setUserName(resultSet.getString(7));
        user.setPassword(resultSet.getString(8));
        user.setAge(resultSet.getInt(9));
        return user;
    }
}
