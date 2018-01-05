package model.dao.utils;

import model.entity.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Vikki on 03.01.2018.
 */
public class ItemUtils {

    public static void setItemToStatement(Item item, PreparedStatement statement, int n) throws SQLException {
        if(item != null){
            statement.setLong(n, item.getId());
        }else{
            statement.setNull(n, Types.NULL);
        }
    }

    public static void setItemToStatement(Long item, PreparedStatement statement, int n) throws SQLException {
        if(item != null){
            statement.setLong(n, item);
        }else{
            statement.setNull(n, Types.NULL);
        }
    }
}
