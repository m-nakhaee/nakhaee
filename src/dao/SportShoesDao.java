package dao;

import dto.products.SportShoes;

import java.sql.SQLException;

public class SportShoesDao extends Dao {
    static SportShoesDao singleToneInstance = new SportShoesDao();

    private SportShoesDao() {
    }

    public static SportShoesDao getInstance() {
        return singleToneInstance;
    }

    @Override
    public SportShoes getAnItemFromResultSet() throws SQLException {
        SportShoes sportShoes = new SportShoes();
        sportShoes.setId(resultSet.getInt(1));
        sportShoes.setName(resultSet.getString(2));
        sportShoes.setPrice(resultSet.getDouble(3));
        sportShoes.setCount(resultSet.getInt(4));
        sportShoes.setSize(resultSet.getInt(5));
        sportShoes.setGender(resultSet.getInt(6));
        sportShoes.setTypeOfSportShoe(resultSet.getInt(7));
        return sportShoes;
    }
}
