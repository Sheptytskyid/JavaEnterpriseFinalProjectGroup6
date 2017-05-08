package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.UserDao;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Main.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;
    @Mock
    private RoleService roleService;
    @Mock
    User user;
    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao, roleService);
    }

    @Test
    public void shouldInvokeUserDaoWhenCreateUser() throws Exception {
        userService.createUser(user);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void createUserByEmailAndPassword() throws Exception {
        String email = "admin@example.com";
        String password = "1111";
        userService.createUser(email, password);
        verify(roleService, times(1)).findOrCreateRole("ROLE_USER");
        User user = new User();
        user.setName("Admin");
        user.setEmail(email);
        Set<Role> roles = new HashSet<>();
        roles.add(null);
        user.setRoles(roles);
        user.setPassword(password);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void shouldInvokeUserDaoWhenUpdateUser() throws Exception {
        userService.updateUser(user);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void shouldInvokeUserDaoWhenDeleteUser() throws Exception {
        userService.deleteUser(1L);
        verify(userDao, times(1)).delete(1L);
    }

    @Test
    public void shouldInvokeUserDaoWhenGetUserById() throws Exception {
        userService.getUserById(1L);
        verify(userDao, times(1)).findOne(1L);
    }

    @Test
    public void shouldInvokeUserDaoWhenGetAllUsers() throws Exception {
        when(userDao.findAll()).thenReturn(new ArrayList<>());
        userService.getAllUsers();
        verify(userDao,times(1)).findAll();
    }

    @Test
    public void shouldInvokeUserDaoWhenGetUserByEmail() throws Exception {
        userService.getUserByEmail("");
        verify(userDao, times(1)).findByEmail("");
    }
}
