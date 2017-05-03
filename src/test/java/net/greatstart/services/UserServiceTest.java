package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.RoleDao;
import net.greatstart.dao.UserDao;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Main.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;
    @InjectMocks
    private UserService userService;
    private User user = new User();

    @Test
    public void createUser() throws Exception {
        userService.createUser(user);
        verify(userDao).create(user);
    }

    @Test
    public void createUserByEmailAndPassword() throws Exception {
        String email = "admin@example.com";
        String password = "1111";
        userService.createUser(email, password);
        verify(roleDao).getByName("ROLE_USER");
        User user = new User();
        user.setName("Admin");
        user.setEmail(email);
        Set<Role> set = new HashSet<>();
        set.add(null);
        user.setRoles(set);
        user.setPassword(password);
        verify(userDao).create(user);
    }

    @Test
    public void updateUser() throws Exception {
        userService.updateUser(1L);
        verify(userDao).getById(1L);
        verify(userDao).update(null);
    }

    @Test
    public void deleteUser() throws Exception {
        userService.deleteUser(1L);
        verify(userDao).getById(1L);
        verify(userDao).delete(null);
    }

    @Test
    public void getUserById() throws Exception {
        userService.getUserById(1L);
        verify(userDao).getById(1L);
    }

    @Test
    public void getAllUsers() throws Exception {
        userService.getAllUsers();
        verify(userDao).getAll();
    }

    @Test
    public void getUserByEmail() throws Exception {
        userService.getUserByEmail("");
        verify(userDao).getByEmail("");
    }

}