package net.greatstart.dao;

import net.greatstart.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

    Role getByName(String name);
}
