package dao;

import dto.products.Book;

import java.sql.SQLException;

public class BookDao extends Dao {
    static BookDao singleToneInstance = new BookDao();

    private BookDao() {
    }

    public static BookDao getInstance() {
        return singleToneInstance;
    }

    @Override
    public Book getAnItemFromResultSet() throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt(1));
        book.setName(resultSet.getString(2));
        book.setPrice(resultSet.getDouble(3));
        book.setCount(resultSet.getInt(4));
        book.setPublisherName(resultSet.getString(5));
        book.setPublisherNumber(resultSet.getInt(6));
        book.setWriter(resultSet.getString(7));
        return book;
    }
}
