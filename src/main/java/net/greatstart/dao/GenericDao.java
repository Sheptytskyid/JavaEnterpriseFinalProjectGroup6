package net.greatstart.dao;

import java.util.List;

public interface GenericDao<T> {

    boolean create(T item);

    boolean update(T item);

    boolean delete(T item);

    T getById(long id);

    List<T> getAll();

}
