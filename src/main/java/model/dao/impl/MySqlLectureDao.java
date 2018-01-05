package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.LectureDao;
import model.dao.utils.ItemUtils;
import model.entity.Lecture;
import model.entity.builders.LectureBuilder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.dao.utils.constants.AddressConstants.TABLE;
import static model.dao.utils.constants.LectureConstants.*;

/**
 * Created by Vikki on 03.01.2018.
 */
public class MySqlLectureDao extends AbstractDao<Lecture> implements LectureDao {

    public MySqlLectureDao(DataSource dataSource){
        super(TABLE, dataSource);
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
