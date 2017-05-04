package net.greatstart.dao;

import net.greatstart.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {

    Role getByName(String name);
}
