package model.dao;

import model.entity.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public interface GenericDao<T> {
    void create(T entity) throws SQLException;
    T findById(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
    T findByEntity(Item entity) throws SQLException;
    List<T> findAllByItem(Item item) throws SQLException;
}
