package net.greatstart.dao.hibernate;

import net.greatstart.dao.UserDao;
import net.greatstart.model.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean create(User item) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(User item) {
        return false;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
