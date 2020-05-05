package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao {
    String query;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public Object search(int id, String table) {
        try {
            connection = MyConnection.getConnection();
            query = "select * from " + table + " where id = " + id;
            getResultSetFromDB();
            Object object = null;
            if (resultSet.next()) object = getAnItemFromResultSet();
            closePreparedStatementAndConnection();
            return object;
        } catch (SQLException e) {
            return null;
        }
    }

    public ResultSet getResultSetFromDB() throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public List<Object> getEntireTable(String table) {
        try {
            connection = MyConnection.getConnection();
            query = "select * from " + table;
            getResultSetFromDB();
            List<Object> list = getEntireTableFromResultSet();
            closePreparedStatementAndConnection();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    private List<Object> getEntireTableFromResultSet() throws SQLException {
        List<Object> list = new ArrayList<>();
        while (resultSet.next())
            list.add(getAnItemFromResultSet());
        return list;
    }

    public void closePreparedStatementAndConnection() throws SQLException {
        preparedStatement.close();
        connection.close();
    }

    public abstract Object getAnItemFromResultSet() throws SQLException;

}
