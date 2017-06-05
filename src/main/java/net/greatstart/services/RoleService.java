package net.greatstart.services;

import net.greatstart.dao.RoleDao;
import net.greatstart.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business logic layer for {@link net.greatstart.model.Role}.
 */

@Service
@Transactional
public class RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role addRole(Role role) {
        return roleDao.save(role);
    }

    public Role findOrCreateRole(String name) {
        Role role = roleDao.getByName(name);
        if (role == null) {
            role = new Role(name);
            addRole(role);
        }
        return role;
    }
}
