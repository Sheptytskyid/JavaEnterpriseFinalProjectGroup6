/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Role}.
 */
package net.greatstart.dao;

import net.greatstart.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

    Role getByName(String name);
}
