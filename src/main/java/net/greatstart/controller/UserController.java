package net.greatstart.controller;

import net.greatstart.model.User;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public boolean createUser(User user) {
        return userService.createUser(user);
    }

    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    public boolean deleteUser(User user) {
        return userService.deleteUser(user);
    }

    public User getUserById(long id) {
        return userService.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
