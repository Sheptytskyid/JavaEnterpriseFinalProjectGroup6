package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean createUser(User user) {
        return userDao.create(user);
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

    public User getByUsername(String name) {
        return getUserById(1L);
    }
}
