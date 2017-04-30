package net.greatstart.dao;

import net.greatstart.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role getByName(String name);
}
