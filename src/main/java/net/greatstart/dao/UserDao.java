package net.greatstart.dao;

import net.greatstart.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
