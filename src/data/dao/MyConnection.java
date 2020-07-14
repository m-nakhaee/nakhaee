package data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MyConnection {

    static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shop", "root", null);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}