package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.UserDao;
import model.dao.utils.ItemUtils;
import model.dao.utils.QueryBuilder;
import model.dao.utils.constants.RegisterOnEventConstants;
import model.entity.Event;
import model.entity.Role;
import model.entity.User;
import model.entity.builders.UserBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.dao.utils.constants.AddressConstants.TABLE;
import static model.dao.utils.constants.UserConstants.*;

/**
 * Created by Vikki on 03.01.2018.
 */
public class MySqlUserDao extends AbstractDao<User> implements UserDao{

    public MySqlUserDao(DataSource dataSource){
        super(TABLE, dataSource);
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[]{NAME, LOGIN, PASSWORD, EMAIL, ROLE_ID, MARK};
    }

    public List<Event.RegisteredUser> findAllByItem(Event entity) throws SQLException{
        if (entity == null) return null;

        String query = QueryBuilder.findWithConditions(TABLE, RegisterOnEventConstants.IS_PRESENT,
                    RegisterOnEventConstants.TABLE, RegisterOnEventConstants.EVENT_ID);
        List<Event.RegisteredUser> items = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Event.RegisteredUser user = (Event.RegisteredUser)getEntityFromResultSet(resultSet);
                user.setPresent(resultSet.getBoolean(RegisterOnEventConstants.IS_PRESENT));
                items.add(user);
            }
            return items;
        }
    }

    @Override
    protected void setEntityProperties(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getLogin());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getEmail());
        statement.setLong(5, entity.getRole().getId());
        ItemUtils.setItemToStatement(entity.getMark(), statement, 6);
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        UserBuilder userBuilder = new UserBuilder();
        return userBuilder.setId(resultSet.getLong(ID))
                .setName(resultSet.getString(NAME))
                .setLogin(resultSet.getString(LOGIN))
                .setPassword(resultSet.getString(PASSWORD))
                .setEmail(resultSet.getString(EMAIL))
                .setRole(Role.findById(resultSet.getLong(ROLE_ID)))
                .setMark(resultSet.getLong(MARK))
                .buildProxy();
    }
}
