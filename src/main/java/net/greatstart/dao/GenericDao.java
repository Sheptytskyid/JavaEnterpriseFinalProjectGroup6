package net.greatstart.dao;

import java.util.List;

public interface GenericDao<T> {

    boolean create(T item);

    boolean delete(long id);

    boolean update(T item);

    T getById(long id);

    List<T> getAll();

}
