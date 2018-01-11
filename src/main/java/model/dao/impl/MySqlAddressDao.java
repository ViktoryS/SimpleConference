package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.AddressDao;
import model.dao.utils.QueryBuilder;
import model.dao.utils.constants.EventConstants;
import model.entity.Address;
import model.entity.Event;
import model.entity.builders.AddressBuilder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.dao.utils.constants.AddressConstants.*;

/**
 * Created by Vikki on 23.12.2017.
 */
public class MySqlAddressDao extends AbstractDao<Address> implements AddressDao {

    public MySqlAddressDao(DataSource dataSource){
        super(TABLE, dataSource);
    }

    @Override
    public Address findByEvent(Event event) throws SQLException {
        String query = QueryBuilder.findWithCondition(TABLE, EventConstants.TABLE, EventConstants.ID);
        return super.findByQuery(query, event.getId());
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{CITY, STREET, HOUSE};
    }

    @Override
    public void setEntityProperties(Address entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getCity());
        statement.setString(2, entity.getStreet());
        statement.setString(3, entity.getHouse());
    }

    @Override
    public Address getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        AddressBuilder builder = new AddressBuilder();
        return builder.setId(resultSet.getLong(ID))
                .setCity(resultSet.getString(CITY))
                .setStreet(resultSet.getString(STREET))
                .setHouse(resultSet.getString(HOUSE))
                .buildProxy();
    }
}
