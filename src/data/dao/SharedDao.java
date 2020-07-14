package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SharedDao {
    String query;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void getResultSetFromDB() throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
    }


    public void closePreparedStatementAndConnection() throws SQLException {
        preparedStatement.close();
        connection.close();
    }

}
