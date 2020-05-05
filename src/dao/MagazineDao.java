package dao;

import dto.products.Magazine;

import java.sql.SQLException;

public class MagazineDao extends Dao {
    static MagazineDao singleToneInstance = new MagazineDao();

    private MagazineDao() {
    }

    public static MagazineDao getInstance() {
        return singleToneInstance;
    }

    @Override
    public Magazine getAnItemFromResultSet() throws SQLException {
        Magazine magazine = new Magazine();
        magazine.setId(resultSet.getInt(1));
        magazine.setName(resultSet.getString(2));
        magazine.setPrice(resultSet.getDouble(3));
        magazine.setCount(resultSet.getInt(4));
        magazine.setPublisherName(resultSet.getString(5));
        magazine.setPublisherNumber(resultSet.getInt(6));
        magazine.setConcessionaire(resultSet.getString(7));
        magazine.setType(resultSet.getInt(7));
        return magazine;
    }
}
