package net.greatstart.dao;

import net.greatstart.model.User;

public interface UserDao extends GenericDAO<User> {

    User getByEmail(String email);
}
