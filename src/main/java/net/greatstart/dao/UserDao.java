package net.greatstart.dao;

import net.greatstart.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.User}.
 */

public interface UserDao extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
}
