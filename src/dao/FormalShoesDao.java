package dao;

import dto.products.FormalShoes;

import java.sql.SQLException;
import java.util.List;

public class FormalShoesDao extends Dao {
    static FormalShoesDao singleToneInstance = new FormalShoesDao();

    private FormalShoesDao() {
    }

    public static FormalShoesDao getInstance() {
        return singleToneInstance;
    }


    @Override
    public FormalShoes getAnItemFromResultSet() throws SQLException {
        FormalShoes formalShoes = new FormalShoes();
        formalShoes.setId(resultSet.getInt(1));
        formalShoes.setName(resultSet.getString(2));
        formalShoes.setPrice(resultSet.getDouble(3));
        formalShoes.setCount(resultSet.getInt(4));
        formalShoes.setSize(resultSet.getInt(5));
        formalShoes.setGender(resultSet.getInt(6));
        formalShoes.setType(resultSet.getInt(7));
        return formalShoes;
    }
}
