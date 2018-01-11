package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.LectureDao;
import model.dao.utils.ItemUtils;
import model.dao.utils.QueryBuilder;
import model.dao.utils.constants.EventConstants;
import model.dao.utils.constants.LectureConstants;
import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;
import model.entity.builders.LectureBuilder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static model.dao.utils.constants.LectureConstants.*;

/**
 * Created by Vikki on 03.01.2018.
 */
public class MySqlLectureDao extends AbstractDao<Lecture> implements LectureDao {

    public MySqlLectureDao(DataSource dataSource){
        super(TABLE, dataSource);
    }

    @Override
    public Lecture findByEvent(Event event) throws SQLException {
        String query = QueryBuilder.findWithCondition(TABLE, EventConstants.TABLE, EventConstants.ID);
        return super.findByQuery(query, event.getId());
    }

    @Override
    public List<Lecture> findByUser(User user) throws SQLException {
        String query = QueryBuilder.findWithCondition(TABLE, LectureConstants.USER_ID);
        return super.findAllByQuery(query, user.getId());
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[]{TOPIC, USER_ID};
    }

    @Override
    protected void setEntityProperties(Lecture entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getTopic());
        ItemUtils.setItemToStatement(entity.getSpiker(), statement, 3);
    }

    @Override
    protected Lecture getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        LectureBuilder builder = new LectureBuilder();
        return builder.setId(resultSet.getLong(ID))
                .setTopic(resultSet.getString(TOPIC))
                .buildProxy();
    }
}
