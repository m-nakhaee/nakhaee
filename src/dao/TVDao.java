package dao;

import dto.products.TV;

import java.sql.SQLException;

public class TVDao extends Dao {
    static TVDao singleToneInstance = new TVDao();

    private TVDao() {
    }

    public static TVDao getInstance() {
        return singleToneInstance;
    }

    @Override
    public TV getAnItemFromResultSet() throws SQLException {
        TV tv = new TV();
        tv.setId(resultSet.getInt(1));
        tv.setName(resultSet.getString(2));
        tv.setPrice(resultSet.getDouble(3));
        tv.setCount(resultSet.getInt(4));
        tv.setWat(resultSet.getInt(5));
        tv.setVoltage(resultSet.getInt(6));
        tv.setInch(resultSet.getInt(7));
        tv.setType(resultSet.getInt(7));
        return tv;
    }
}
