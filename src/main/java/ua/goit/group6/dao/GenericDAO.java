package ua.goit.group6.dao;

import java.util.List;

public interface GenericDAO<T> {

    void create(T item);

    boolean delete(long id);

    boolean update(T item);

    T getById(long id);

    T getByEmail(String email);

    List<T> getAll();

}
