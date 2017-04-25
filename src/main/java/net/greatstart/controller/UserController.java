package net.greatstart.controller;

import net.greatstart.model.User;
import net.greatstart.services.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public boolean createUser(User user) {
        return userService.createUser(user);
    }

    public boolean updateUser(long id) {
        return userService.updateUser(id);
    }

    public boolean deleteUser(long id) {
        return userService.deleteUser(id);
    }

    public User getUserById(long id) {
        return userService.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
