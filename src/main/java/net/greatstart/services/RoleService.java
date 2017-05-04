package net.greatstart.services;

import net.greatstart.dao.RoleDao;
import net.greatstart.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void addRole(Role role) {
        roleDao.create(role);
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
