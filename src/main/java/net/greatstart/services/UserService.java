package net.greatstart.services;

import net.greatstart.dao.PasswordTokenDao;
import net.greatstart.dao.UserDao;
import net.greatstart.model.PasswordResetToken;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private UserDao userDao;
    private RoleService roleService;
    private PasswordTokenDao passwordTokenDao;

    @Autowired
    public UserService(UserDao userDao, RoleService roleService, PasswordTokenDao passwordTokenDao) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordTokenDao = passwordTokenDao;
    }

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User createUser(String email, String password) {
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
        return createUser(user);
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenDao.save(myToken);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
    }

    public User updateUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(long id) {
        userDao.delete(id);
    }

    public User getUserById(long id) {
        return userDao.findOne(id);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
