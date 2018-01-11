package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.EventDao;
import model.dao.utils.ItemUtils;
import model.dao.utils.QueryBuilder;
import model.dao.utils.constants.RegisterOnEventConstants;
import model.entity.Address;
import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;
import model.entity.builders.EventBuilder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static model.dao.utils.constants.EventConstants.*;

/**
 * Created by Vikki on 03.01.2018.
 */
public class MySqlEventDao extends AbstractDao<Event> implements EventDao{

    public MySqlEventDao(DataSource dataSource) {
        super(TABLE, dataSource);
    }

    @Override
    public List<Event> findByUser(User user) throws SQLException {
        String query = QueryBuilder.findWithConditionsManyToMany(tableName, RegisterOnEventConstants.EVENT_ID,
                RegisterOnEventConstants.TABLE, RegisterOnEventConstants.USER_ID);
        return super.findAllByQuery(query, user.getId());
    }

    @Override
    public List<Event> findByLecture(Lecture lecture) throws SQLException {
        String query = QueryBuilder.findWithCondition(TABLE, LECTURE_ID);
        return super.findAllByQuery(query, lecture.getId());
    }

    @Override
    public List<Event> findByAddress(Address address) throws SQLException {
        String query = QueryBuilder.findWithCondition(TABLE, ADDRESS_ID);
        return super.findAllByQuery(query, address.getId());
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[]{NAME, DATE, ADDRESS_ID, LECTURE_ID};
    }

    @Override
    protected void setEntityProperties(Event entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getName());
        statement.setDate(3, Date.valueOf(entity.getDate()));
        ItemUtils.setItemToStatement(entity.getAddress(), statement, 4);
        ItemUtils.setItemToStatement(entity.getLecture(), statement, 5);
    }

    @Override
    protected Event getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        EventBuilder builder = new EventBuilder();
        return builder.setId(resultSet.getLong(ID))
                .setName(resultSet.getString(NAME))
                .setDate(resultSet.getDate(DATE).toLocalDate())
                .buildProxy();
    }
}
