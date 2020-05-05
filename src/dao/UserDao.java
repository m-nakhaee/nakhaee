package dao;

import dto.User;

import java.sql.SQLException;

public class UserDao extends Dao {
    static UserDao singleToneInstance = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return singleToneInstance;
    }

    public void insertUser(User user) {
        try {
            connection = MyConnection.getConnection();
            insertUserIntoTable(user);
            closePreparedStatementAndConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User search(String userName) {
        try {
            connection = MyConnection.getConnection();
            query = "SELECT* from shop_user WHERE user_name = '" + userName + "'";
            getResultSetFromDB();
            User user = null;
            if (resultSet.next()) user = getAnItemFromResultSet();
            closePreparedStatementAndConnection();
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    private void insertUserIntoTable(User user) throws SQLException {
        query = "insert into shop_user (first_name, last_name, phone_number, email, address, user_name, password) " +
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

    @Override
    public User getAnItemFromResultSet() throws SQLException {
        User user = new User();
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setPhoneNumber(resultSet.getInt(4));
        user.setEmail(resultSet.getString(5));
        user.setAddress(resultSet.getString(6));
        user.setUserName(resultSet.getString(7));
        user.setPassword(resultSet.getString(8));
        return user;
    }
}
