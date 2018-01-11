package model.dao;

import model.entity.Address;
import model.entity.Event;

import java.sql.SQLException;

/**
 * Created by Vikki on 23.12.2017.
 */
public interface AddressDao extends GenericDao<Address> {
    Address findByEvent(Event event) throws SQLException;
}
