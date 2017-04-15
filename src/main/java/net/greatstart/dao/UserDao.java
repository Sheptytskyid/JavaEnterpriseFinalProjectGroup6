package net.greatstart.dao;

import net.greatstart.model.User;

public interface UserDao extends GenericDao<User> {

    User getByEmail(String email);
}
