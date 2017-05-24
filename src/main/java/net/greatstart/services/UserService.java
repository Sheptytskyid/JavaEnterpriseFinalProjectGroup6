package net.greatstart.services;

import net.greatstart.dao.UserDao;
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

    @Autowired
    public UserService(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
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

    public User changeUserPassword(User user, String password) {
        user.setPassword(password);
        return userDao.save(user);
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public String getInitials(String firstName, String lastName) {
        StringBuilder initials = new StringBuilder();
        if (lastName != null && !lastName.isEmpty()) {
            initials.append(firstName.substring(0, 1).toUpperCase())
                    .append(".")
                    .append(lastName.substring(0, 1).toUpperCase())
                    .append(".");
        } else {
            initials.append(firstName.substring(0, 1).toUpperCase());
        }
        return initials.toString();
    }
}
