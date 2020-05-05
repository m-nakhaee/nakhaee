package dao;

import dto.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDao extends Dao {
    private String userName;

    public CartDao(String userName) {
        this.userName = userName;
    }

    public void addToCart(int productID) {
        query = "select * from cart where user_name = '" + userName + "' and product_id = " + productID;
        try {
            connection = MyConnection.getConnection();
            getResultSetFromDB();
            if (resultSet.next()) {
                increaseCount(productID);
            } else {
                query = "insert into cart (product_id, order_count, user_name) values (" +
                        productID + ",1, '" + userName + "\')";
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            closePreparedStatementAndConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void increaseCount(int productID) throws SQLException {
        int count = resultSet.getInt(3);
        count = count + 1;
        query = "UPDATE cart set order_count = "
                + count + " WHERE user_name = '" + userName + "' and product_id = " + productID;
    }

    public boolean removeFromCart(int productID) {
        query = "select * from cart where user_name = '" + userName + "' and product_id = " + productID;
        try {
            connection = MyConnection.getConnection();
            getResultSetFromDB();
            if (resultSet.next()) {
                return decreaseCount(productID);
            } else return false;
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean decreaseCount(int productID) throws SQLException {
        int count = resultSet.getInt(3);
        count = count - 1;
        if (count == 0)
            query = "delete  from cart WHERE user_name = '" + userName + "' and product_id = " + productID;
        else
            query = "UPDATE cart set order_count = "
                    + count + " WHERE user_name = '" + userName + "' and product_id = " + productID;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        closePreparedStatementAndConnection();
        return true;
    }

    public double getTotalCost() {
        query = "select product_id , order_count from cart where user_name = '" + userName + '\'';
        try {
            connection = MyConnection.getConnection();
            getResultSetFromDB();
            double costOfBill = calculateCostOfBill();
            closePreparedStatementAndConnection();
            return costOfBill;
        } catch (SQLException e) {
            return 0;
        }
    }

    private double calculateCostOfBill() throws SQLException {
        double priceOfProduct = 0;
        int countOfProduct;
        int productId;
        ResultSet localResultSet;
        String subQuery = "";
        double costOfCart = 0;
        while (resultSet.next()) {
            productId = resultSet.getInt(1);
            countOfProduct = resultSet.getInt(2);
            subQuery = setSubQuery(productId, subQuery);
            preparedStatement = connection.prepareStatement(subQuery);
            localResultSet = preparedStatement.executeQuery();
            if (localResultSet.next())
                priceOfProduct = localResultSet.getDouble(1);
            costOfCart = costOfCart + (priceOfProduct * countOfProduct);
        }
        return costOfCart;
    }

    private String setSubQuery(int productId, String subQuery) {
        if (productId >= 1000 && productId <= 1500)
            subQuery = "select price from tv where id = " + productId;
        else if (productId > 1500 && productId <= 1999)
            subQuery = "select price from radio where id = " + productId;
        else if (productId >= 2000 && productId <= 2500)
            subQuery = "select price from formal_shoes where id = " + productId;
        else if (productId > 2500 && productId <= 2999)
            subQuery = "select price from sport_shoes where id = " + productId;
        else if (productId >= 3000 && productId <= 3500)
            subQuery = "select price from book where id = " + productId;
        else if (productId > 3500 && productId <= 3999)
            subQuery = "select price from magazine where id = " + productId;
        return subQuery;
    }

    @Override
    public Cart getAnItemFromResultSet() throws SQLException {
        return null;
    }


}
