package data.dao;

import data.dto.products.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends SharedDao {

    public Product search(int id) throws SQLException { //ask for returning
        connection = MyConnection.getConnection();
        Product product = null;
        if (id >= 1000 && id < 1500) product = searchInTVs(id);
        if (id >= 1500 && id < 2000) product = searchInRadios(id);
        if (id >= 2000 && id < 2500) product = searchInSportShoes(id);
        if (id >= 2500 && id < 3000) product = searchInFormalShoes(id);
        if (id >= 3000 && id < 3500) product = searchInBooks(id);
        if (id >= 3500 && id < 4000) product = searchInMagazines(id);
        return product;
    }

    private Magazine searchInMagazines(int id) throws SQLException {
        Magazine magazine = new Magazine();
        query = "SELECT p.*, a.publisher_name, a.publisher_number, a.concessionaire, a.type FROM\n" +
                "((SELECT * from product WHERE id = " + id + ") as p\n" +
                " join \n" +
                " (SELECT readable_stuffs.*, magazine.concessionaire, a.type from (readable_stuffs join magazine " +
                "on readable_stuffs.id = magazine.id))as a\n" +
                " on p.id = a.id ) ";
        getResultSetFromDB();
        if (resultSet.next()) {
            setMagazineParam(magazine);
            return magazine;
        }
        return null;
    }

    private Book searchInBooks(int id) throws SQLException {
        Book book = new Book();
        query = "SELECT p.*, a.publisher_name, a.publisher_number, a.writer FROM\n" +
                "((SELECT * from product WHERE id = " + id + ") as p\n" +
                " join \n" +
                " (SELECT readable_stuffs.*, book.writer from (readable_stuffs join book " +
                "on readable_stuffs.id = book.id))as a\n" +
                " on p.id = a.id ) ";
        getResultSetFromDB();
        if (resultSet.next()) {
            setBookParam(book);
            return book;
        }
        return null;
    }

    private FormalShoes searchInFormalShoes(int id) throws SQLException {
        FormalShoes formalShoes = new FormalShoes();
        query = "SELECT p.*, a.size, a.gender, a.type FROM\n" +
                "((SELECT * from product WHERE id = " + id + ") as p\n" +
                " join \n" +
                " (SELECT shoes.*, formal_shoes.type from (shoes join formal_shoes " +
                "on shoes.id = formal_shoes.id))as a\n" +
                " on p.id = a.id ) ";
        getResultSetFromDB();
        if (resultSet.next()) {
            setFormalShoesParam(formalShoes);
            return formalShoes;
        }
        return null;
    }

    private SportShoes searchInSportShoes(int id) throws SQLException {
        SportShoes sportShoes = new SportShoes();
        query = "SELECT p.*, a.size, a.gender, a.type FROM\n" +
                "((SELECT * from product WHERE id = " + id + ") as p\n" +
                " join \n" +
                " (SELECT shoes.*, sport_shoes.type from (shoes join sport_shoes " +
                "on shoes.id = sport_shoes.id))as a\n" +
                " on p.id = a.id ) ";
        getResultSetFromDB();
        if (resultSet.next()) {
            setSportShoesParam(sportShoes);
            return sportShoes;
        }
        return null;
    }

    private Radio searchInRadios(int id) throws SQLException {
        Radio radio = new Radio();
        query = "SELECT p.*, a.wat, a.voltage, a.antenna_power, a.type FROM\n" +
                "((SELECT * from product WHERE id = " + id + ") as p\n" +
                " join \n" +
                " (SELECT electrical.*, radio.antenna_power, radio.type from (electrical join radio" +
                " on electrical.id = radio.id))as a\n" +
                " on p.id = a.id ) ";
        getResultSetFromDB();
        if (resultSet.next()) {
            setRadioParam(radio);
            return radio;
        }
        return null;
    }

    private TV searchInTVs(int id) throws SQLException {
        TV tv = new TV();
        query = "SELECT p.*, a.wat, a.voltage, a.inch, a.type FROM\n" +
                "((SELECT * from product WHERE id = " + id + ") as p\n" +
                " join \n" +
                " (SELECT electrical.*, tv.inch, tv.type from (electrical join tv on electrical.id = tv.id))as a\n" +
                " on p.id = a.id ) ";
        getResultSetFromDB();
        if (resultSet.next()) {
            setTVParam(tv);
            return tv;
        }
        return null;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        connection = MyConnection.getConnection();
        getTVs(productList);
        getRadios(productList);
        getSportShoes(productList);
        getFormalShoes(productList);
        getBooks(productList);
        getMagazines(productList);
        closePreparedStatementAndConnection();
        return productList;
    }

    private void getTVs(List<Product> productList) throws SQLException {
        query = "select product.*, etv.wat, etv.voltage, etv.inch, etv.type from\n" +
                "(product\n" +
                "join\n" +
                "(select electrical.*, tv.inch, tv.type FROM\n" +
                "electrical join tv on electrical.id = tv.id) as etv\n" +
                "on product.id = etv.id\n" +
                ")\n";
        getResultSetFromDB();
        while (resultSet.next()) {
            TV tv = new TV();
            setTVParam(tv);
            productList.add(tv);
        }
    }

    private void setTVParam(TV tv) throws SQLException {
        tv.setId(resultSet.getInt(1));
        tv.setName(resultSet.getString(2));
        tv.setPrice(resultSet.getDouble(3));
        tv.setCount(resultSet.getInt(4));
        tv.setWat(resultSet.getInt(5));
        tv.setVoltage(resultSet.getInt(6));
        tv.setInch(resultSet.getInt(7));
        tv.setType(resultSet.getInt(8));
    }

    private void getRadios(List<Product> productList) throws SQLException {
        query = "select product.*, er.wat, er.voltage, er.antenna_power, er.type from\n" +
                "(product\n" +
                "join\n" +
                "(select electrical.*, radio.antenna_power, radio.type FROM\n" +
                "electrical join radio on electrical.id = radio.id) as er\n" +
                "on product.id = er.id\n" +
                ")\n";
        getResultSetFromDB();
        while (resultSet.next()) {
            Radio radio = new Radio();
            setRadioParam(radio);
            productList.add(radio);
        }
    }

    private void setRadioParam(Radio radio) throws SQLException {
        radio.setId(resultSet.getInt(1));
        radio.setName(resultSet.getString(2));
        radio.setPrice(resultSet.getDouble(3));
        radio.setCount(resultSet.getInt(4));
        radio.setWat(resultSet.getInt(5));
        radio.setVoltage(resultSet.getInt(6));
        radio.setAntennaPower(resultSet.getInt(7));
        radio.setType(resultSet.getInt(8));
    }

    private void getSportShoes(List<Product> productList) throws SQLException {
        query = "select product.*, sport.size, sport.gender, sport.type from\n" +
                "(product\n" +
                "join\n" +
                "(select shoes.*, sport_shoes.type FROM\n" +
                "shoes join sport_shoes on shoes.id = sport_shoes.id) as sport\n" +
                "on product.id = sport.id\n" +
                ")\n";
        getResultSetFromDB();
        while (resultSet.next()) {
            SportShoes sportShoes = new SportShoes();
            setSportShoesParam(sportShoes);
            productList.add(sportShoes);
        }
    }

    private void setSportShoesParam(SportShoes sportShoes) throws SQLException {
        sportShoes.setId(resultSet.getInt(1));
        sportShoes.setName(resultSet.getString(2));
        sportShoes.setPrice(resultSet.getDouble(3));
        sportShoes.setCount(resultSet.getInt(4));
        sportShoes.setSize(resultSet.getInt(5));
        sportShoes.setGender(resultSet.getInt(6));
        sportShoes.setTypeOfSportShoe(resultSet.getInt(7));
    }

    private void getFormalShoes(List<Product> productList) throws SQLException {
        query = "select product.*, formal.size, formal.gender, formal.type from\n" +
                "(product\n" +
                "join\n" +
                "(select shoes.*, formal_shoes.type FROM\n" +
                "shoes join formal_shoes on shoes.id = formal_shoes.id) as formal\n" +
                "on product.id = formal.id\n" +
                ")\n";
        getResultSetFromDB();
        while (resultSet.next()) {
            FormalShoes formalShoes = new FormalShoes();
            setFormalShoesParam(formalShoes);
            productList.add(formalShoes);
        }
    }

    private void setFormalShoesParam(FormalShoes formalShoes) throws SQLException {
        formalShoes.setId(resultSet.getInt(1));
        formalShoes.setName(resultSet.getString(2));
        formalShoes.setPrice(resultSet.getDouble(3));
        formalShoes.setCount(resultSet.getInt(4));
        formalShoes.setSize(resultSet.getInt(5));
        formalShoes.setGender(resultSet.getInt(6));
        formalShoes.setType(resultSet.getInt(7));
    }

    private void getBooks(List<Product> productList) throws SQLException {
        query = "select product.*, rBook.publisher_name, rBook.publisher_number, rBook.writer from\n" +
                "(product\n" +
                "join\n" +
                "(select readable_stuffs.*, book.writer FROM\n" +
                "readable_stuffs join book on readable_stuffs.id = book.id) as rBook\n" +
                "on product.id = rBook.id\n" +
                ")\n";
        getResultSetFromDB();
        while (resultSet.next()) {
            Book book = new Book();
            setBookParam(book);
            productList.add(book);
        }
    }

    private void setBookParam(Book book) throws SQLException {
        book.setId(resultSet.getInt(1));
        book.setName(resultSet.getString(2));
        book.setPrice(resultSet.getDouble(3));
        book.setCount(resultSet.getInt(4));
        book.setPublisherName(resultSet.getString(5));
        book.setPublisherNumber(resultSet.getInt(6));
        book.setWriter(resultSet.getString(7));
    }

    private void getMagazines(List<Product> productList) throws SQLException {
        query = "select product.*, mag.publisher_name, mag.publisher_number, mag.concessionaire, mag.type from\n" +
                "(product\n" +
                "join\n" +
                "(select readable_stuffs.*, magazine.concessionaire, magazine.type FROM\n" +
                "readable_stuffs join magazine on readable_stuffs.id = magazine.id) as mag\n" +
                "on product.id = mag.id\n" +
                ")\n";
        getResultSetFromDB();
        while (resultSet.next()) {
            Magazine magazine = new Magazine();
            setMagazineParam(magazine);
            productList.add(magazine);
        }
    }

    private void setMagazineParam(Magazine magazine) throws SQLException {
        magazine.setId(resultSet.getInt(1));
        magazine.setName(resultSet.getString(2));
        magazine.setPrice(resultSet.getDouble(3));
        magazine.setCount(resultSet.getInt(4));
        magazine.setPublisherName(resultSet.getString(5));
        magazine.setPublisherNumber(resultSet.getInt(6));
        magazine.setConcessionaire(resultSet.getString(7));
        magazine.setType(resultSet.getInt(8));
    }

    public void updateCount(int id, int count) {
        try {
            connection = MyConnection.getConnection();
            query = "update product set count = count -" + count + " WHERE id =" + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            closePreparedStatementAndConnection();
        } catch (SQLException e) {
        }
    }


}
