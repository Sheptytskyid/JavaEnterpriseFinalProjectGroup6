package net.greatstart.services;

import net.greatstart.dao.RoleDao;
import net.greatstart.dao.UserDao;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public boolean createUser(User user) {
        return userDao.create(user);
    }

    public void createUser(String email, String password) {
        User user = new User();
        int i = email.indexOf('@');
        String name = email.substring(0, i);
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getByName("ROLE_USER"));
        user.setRoles(roles);
        createUser(user);
    }

    public boolean updateUser(long id) {
        User user = getUserById(id);
        return userDao.update(user);
    }

    public boolean deleteUser(long id) {
        User user = getUserById(id);
        return userDao.delete(user);
    }

    public User getUserById(long id) {
        return userDao.getById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
