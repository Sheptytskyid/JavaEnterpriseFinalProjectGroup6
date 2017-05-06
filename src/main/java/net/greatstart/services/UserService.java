package net.greatstart.services;

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
    private RoleService roleService;

    @Autowired
    public UserService(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
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
        roles.add(roleService.findOrCreateRole("ROLE_USER"));
        user.setRoles(roles);
        user.setPhoto(IdenticonGeneratorService.generateByteImage(name, 320, 320));
        createUser(user);
    }

    public boolean updateUser(User user) {
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
