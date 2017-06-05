/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.User}.
 */
package net.greatstart.dao;

import net.greatstart.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
}
