package model.dao;


import model.dao.utils.QueryBuilder;
import model.dao.utils.constants.EventConstants;
import model.dao.utils.constants.LectureConstants;
import model.dao.utils.constants.RegisterOnEventConstants;
import model.entity.Item;
import model.entity.proxy.AddressProxy;
import model.entity.proxy.EventProxy;
import model.entity.proxy.LectureProxy;
import model.entity.proxy.UserProxy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<T extends Item> implements GenericDao<T>{
    protected String tableName;
    protected DataSource dataSource;

    public AbstractDao(String tableName, DataSource dataSource) {
        this.tableName = tableName;
        this.dataSource = dataSource;
    }

    @Override
    public void create(T entity)  throws SQLException{
        String query = QueryBuilder.addEntity(tableName, getPropertyNames());
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityProperties(entity, statement);
            statement.executeUpdate();
        }
    }

    @Override
    public T findById(Long id)  throws SQLException{
        String query = QueryBuilder.findById(tableName);
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getEntityFromResultSet(resultSet);
        }
    }

    @Override
    public List<T> findAll() throws SQLException{
        String query = QueryBuilder.getAll(tableName);
        List<T> result;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(getEntityFromResultSet(resultSet));
            }
            return result;
        }
    }

    @Override
    public void update(T entity) throws SQLException{
        String query = QueryBuilder.update(tableName,getPropertyNames());
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            setEntityProperties(entity, statement);
            statement.setLong(getPropertyNames().length+1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException{
        String query = QueryBuilder.delete(tableName);
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }

    public T findByEntity(Item entity) throws SQLException {
        if(entity == null) return null;
        String query = null;
        if(entity instanceof LectureProxy){
            query = QueryBuilder.findWithCondition(tableName, LectureConstants.TABLE, LectureConstants.ID);
        }else if(entity instanceof EventProxy){
            query = QueryBuilder.findWithCondition(tableName, EventConstants.TABLE, EventConstants.ID);
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, entity.getId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getEntityFromResultSet(resultSet);
        }
    }

    public List<T> findAllByItem(Item entity) throws SQLException {
        if (entity == null) return null;
        String query = null;
        if(entity instanceof LectureProxy){
            query = QueryBuilder.findWithCondition(tableName, LectureConstants.USER_ID);
        }else if(entity instanceof AddressProxy){
            query = QueryBuilder.findWithCondition(tableName, EventConstants.ADDRESS_ID);
        }else if(entity instanceof UserProxy ){
            query = tableName.equals(EventConstants.TABLE) ?
                    QueryBuilder.findWithConditionsManyToMany(tableName, RegisterOnEventConstants.EVENT_ID,
                    RegisterOnEventConstants.TABLE, RegisterOnEventConstants.USER_ID)
                    : QueryBuilder.findWithCondition(tableName, LectureConstants.USER_ID);
        }
        List<T> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getEntityFromResultSet(resultSet));
            }
            return items;
        }
    }
    protected abstract String[] getPropertyNames();
    protected abstract void setEntityProperties(T entity, PreparedStatement statement) throws SQLException;
    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
