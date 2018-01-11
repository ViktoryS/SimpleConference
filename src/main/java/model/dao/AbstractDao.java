package model.dao;


import model.dao.utils.QueryBuilder;
import model.entity.Item;

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

    protected T findByQuery(String query, Long id) throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getEntityFromResultSet(resultSet);
        }
    }

    protected List<T> findAllByQuery(String query, Long id) throws SQLException {
        List<T> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
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
