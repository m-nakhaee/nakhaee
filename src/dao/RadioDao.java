package dao;

import dto.products.Radio;

import java.sql.SQLException;

public class RadioDao extends Dao {
    static RadioDao singleToneInstance = new RadioDao();

    private RadioDao() {
    }

    public static RadioDao getInstance() {
        return singleToneInstance;
    }

    @Override
    public Radio getAnItemFromResultSet() throws SQLException {
        Radio radio = new Radio();
        radio.setId(resultSet.getInt(1));
        radio.setName(resultSet.getString(2));
        radio.setPrice(resultSet.getDouble(3));
        radio.setCount(resultSet.getInt(4));
        radio.setWat(resultSet.getInt(5));
        radio.setVoltage(resultSet.getInt(6));
        radio.setAntennaPower(resultSet.getInt(7));
        radio.setType(resultSet.getInt(7));
        return radio;
    }
}
