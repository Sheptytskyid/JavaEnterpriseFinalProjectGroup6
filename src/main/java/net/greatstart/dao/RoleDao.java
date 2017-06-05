package net.greatstart.dao;

import net.greatstart.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Role}.
 */

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

    Role getByName(String name);
}
