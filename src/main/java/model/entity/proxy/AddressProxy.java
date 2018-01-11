package model.entity.proxy;

import model.dao.EventDao;
import model.dao.factory.DaoFactory;
import model.entity.Address;
import model.entity.Event;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public class AddressProxy extends Address {
    @Override
    public List<Event> getEvents() {
        if(super.getEvents() != null) return super.getEvents();
        EventDao eventDao = DaoFactory.getInstance().createEventDao();
        try {
            return eventDao.findByAddress(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
