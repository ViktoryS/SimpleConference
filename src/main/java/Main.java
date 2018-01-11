import model.dao.factory.DaoFactory;

import java.sql.SQLException;

/**
 * Created by Vikki on 03.01.2018.
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(DaoFactory.getInstance().createUserDao().findAll());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
