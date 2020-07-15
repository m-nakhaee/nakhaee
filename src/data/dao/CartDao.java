package data.dao;

import data.dto.products.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends SharedDao {
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void emptyCart() throws SQLException {
        query = "delete from cart where user_name = '" + userName + '\'';
        connection = MyConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        closePreparedStatementAndConnection();
    }

    public void addToCart(int productID) throws SQLException {
        query = "select * from cart where user_name = '" + userName + "' and product_id = " + productID;
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
    }

    private void increaseCount(int productID) throws SQLException {
        int count = resultSet.getInt(3);
        count = count + 1;
        query = "UPDATE cart set order_count = "
                + count + " WHERE user_name = '" + userName + "' and product_id = " + productID;
    }

    public boolean removeFromCart(int productID) throws SQLException {
        boolean retValue = false;
        query = "select * from cart where user_name = '" + userName + "' and product_id = " + productID;
        connection = MyConnection.getConnection();
        getResultSetFromDB();
        if (resultSet.next()) {
            retValue = decreaseCount(productID);
        }
        closePreparedStatementAndConnection();
        return retValue;

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
        return true;
    }

    public double getTotalCost() throws SQLException {
        query = "select product_id , order_count from cart where user_name = '" + userName + '\'';
        connection = MyConnection.getConnection();
        getResultSetFromDB();
        double costOfBill = calculateCostOfBill();
        closePreparedStatementAndConnection();
        return costOfBill;
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
            subQuery = "select price from product where id = " + productId;
            preparedStatement = connection.prepareStatement(subQuery);
            localResultSet = preparedStatement.executeQuery();
            if (localResultSet.next())
                priceOfProduct = localResultSet.getDouble(1);
            costOfCart = costOfCart + (priceOfProduct * countOfProduct);
        }
        return costOfCart;
    }

    public List<Product> getProducts() throws SQLException {
        int productId;
        List<Product> productList = new ArrayList<>();
        connection = MyConnection.getConnection();
        query = "SELECT c.order_count, product.id, product.name, product.price from \n" +
                "((select * from cart WHERE user_name  = '" + userName +
                "') as c join product on c.product_id = product.id)";
        getResultSetFromDB();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(3));
            productId = resultSet.getInt(2);
            if (productId >= 1000 && productId < 1500)
                getTV(productId, productList);
            if (productId >= 1500 && productId < 2000)
                getRadio(productId, productList);
            if (productId >= 2000 && productId < 2500)
                getSportShoes(productId, productList);
            if (productId >= 2500 && productId < 3000)
                getFormalShoes(productId, productList);
            if (productId >= 3000 && productId < 3500)
                getBook(productId, productList);
            if (productId >= 3500 && productId < 4000)
                getMagazine(productId, productList);

        }
        closePreparedStatementAndConnection();
        return productList;
    }

    private void getMagazine(int productId, List<Product> productList) throws SQLException {
        ResultSet localResultSet;
        Magazine magazine = new Magazine();
        magazine.setId(productId);
        magazine.setName(resultSet.getString(3));
        magazine.setPrice(resultSet.getDouble(4));
        magazine.setCount(resultSet.getInt(1));
        query = "select mag.publisher_name, mag.publisher_number, mag.concessionaire, mag.type\n" +
                "from (product\n" +
                "join (select readable_stuffs.*, magazine.concessionaire, magazine.type FROM\n" +
                "(readable_stuffs join magazine on readable_stuffs.id = magazine.id)" +
                " where magazine.id = " + productId + ")  as mag on product.id = mag.id)";
        preparedStatement = connection.prepareStatement(query);
        localResultSet = preparedStatement.executeQuery();
        if (localResultSet.next()) {
            magazine.setPublisherName(localResultSet.getString(1));
            magazine.setPublisherNumber(localResultSet.getInt(2));
            magazine.setConcessionaire(localResultSet.getString(3));
            magazine.setType(localResultSet.getInt(4));
        }
        productList.add(magazine);
    }

    private void getBook(int productId, List<Product> productList) throws SQLException {
        ResultSet localResultSet;
        Book book = new Book();
        book.setId(productId);
        book.setName(resultSet.getString(3));
        book.setPrice(resultSet.getDouble(4));
        book.setCount(resultSet.getInt(1));
        query = "select rBook.publisher_name, rBook.publisher_number, rBook.writer\n" +
                "from (product\n" +
                "join (select readable_stuffs.*, book.writer FROM\n" +
                "(readable_stuffs join book on readable_stuffs.id = book.id)" +
                " where book.id = " + productId + ") as rBook on product.id = rBook.id)";
        preparedStatement = connection.prepareStatement(query);
        localResultSet = preparedStatement.executeQuery();
        if (localResultSet.next()) {
            book.setPublisherName(localResultSet.getString(1));
            book.setPublisherNumber(localResultSet.getInt(2));
            book.setWriter(localResultSet.getString(3));
        }
        productList.add(book);
    }

    private void getFormalShoes(int productId, List<Product> productList) throws SQLException {
        ResultSet localResultSet;
        FormalShoes formalShoes = new FormalShoes();
        formalShoes.setId(productId);
        formalShoes.setName(resultSet.getString(3));
        formalShoes.setPrice(resultSet.getDouble(4));
        formalShoes.setCount(resultSet.getInt(1));
        query = "select formal.size, formal.gender, formal.type from\n" +
                "(product\n" +
                "join (select shoes.*, formal_shoes.type FROM\n" +
                "(shoes join formal_shoes on shoes.id = formal_shoes.id)" +
                " where  formal_shoes.id = " + productId + ")  as formal on product.id = formal.id)";
        preparedStatement = connection.prepareStatement(query);
        localResultSet = preparedStatement.executeQuery();
        if (localResultSet.next()) {
            formalShoes.setSize(localResultSet.getInt(1));
            formalShoes.setGender(localResultSet.getInt(2));
            formalShoes.setType(localResultSet.getInt(3));
        }
        productList.add(formalShoes);
    }

    private void getSportShoes(int productId, List<Product> productList) throws SQLException {
        ResultSet localResultSet;
        SportShoes sportShoes = new SportShoes();
        sportShoes.setId(productId);
        sportShoes.setName(resultSet.getString(3));
        sportShoes.setPrice(resultSet.getDouble(4));
        sportShoes.setCount(resultSet.getInt(1));
        query = "select sport.size, sport.gender, sport.type from\n" +
                "(product\n" +
                "join (select shoes.*, sport_shoes.type FROM\n" +
                "(shoes join sport_shoes on shoes.id = sport_shoes.id)" +
                " where  sport_shoes.id = " + productId + ")  as sport on product.id = sport.id)";
        preparedStatement = connection.prepareStatement(query);
        localResultSet = preparedStatement.executeQuery();
        if (localResultSet.next()) {
            sportShoes.setSize(localResultSet.getInt(1));
            sportShoes.setGender(localResultSet.getInt(2));
            sportShoes.setTypeOfSportShoe(localResultSet.getInt(3));
        }
        productList.add(sportShoes);
    }

    private void getRadio(int productId, List<Product> productList) throws SQLException {
        ResultSet localResultSet;
        Radio radio = new Radio();
        radio.setId(productId);
        radio.setName(resultSet.getString(3));
        radio.setPrice(resultSet.getDouble(4));
        radio.setCount(resultSet.getInt(1));
        query = "select er.wat, er.voltage, er.antenna_power, er.type from\n" +
                "(product\n" +
                "join (select electrical.*, radio.antenna_power, radio.type FROM\n" +
                "(electrical join radio on electrical.id = radio.id)" +
                " where  radio.id = " + productId + ")  as er on product.id = er.id)";
        preparedStatement = connection.prepareStatement(query);
        localResultSet = preparedStatement.executeQuery();
        if (localResultSet.next()) {
            radio.setWat(localResultSet.getInt(1));
            radio.setVoltage(localResultSet.getInt(2));
            radio.setAntennaPower(localResultSet.getInt(3));
            radio.setType(localResultSet.getInt(4));
        }
        productList.add(radio);
    }

    private void getTV(int productId, List<Product> productList) throws SQLException {
        ResultSet localResultSet;
        TV tv = new TV();
        tv.setId(productId);
        tv.setName(resultSet.getString(3));
        tv.setPrice(resultSet.getDouble(4));
        tv.setCount(resultSet.getInt(1));
        query = "select etv.wat, etv.voltage, etv.inch, etv.type from\n" +
                "(product\n" +
                "join (select electrical.*, tv.inch, tv.type FROM\n" +
                "(electrical join tv on electrical.id = tv.id)" +
                " where  tv.id = " + productId + ")  as etv on product.id = etv.id)";
        preparedStatement = connection.prepareStatement(query);
        localResultSet = preparedStatement.executeQuery();
        if (localResultSet.next()) {
            tv.setWat(localResultSet.getInt(1));
            tv.setVoltage(localResultSet.getInt(2));
            tv.setInch(localResultSet.getInt(3));
            tv.setType(localResultSet.getInt(4));
        }
        productList.add(tv);
    }

}
