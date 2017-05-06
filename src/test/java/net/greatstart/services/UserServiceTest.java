package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    public static final long ID = 1L;
    @Mock
    private UserDao userDao;
    @Mock
    private RoleService roleService;
    @InjectMocks
    private UserService userService;
    private User user = new User();


    @Test
    public void shouldInvokeUserDaoWhenCreateUser() throws Exception {
        when(userDao.save(user)).thenReturn(user);
        assertEquals(userService.createUser(user), user);
    }

    @Test
    public void createUserByEmailAndPassword() throws Exception {
        String email = "admin@example.com";
        String password = "1111";
        String roleName = "ROLE_USER";
        user.setName("Admin");
        user.setEmail(email);
        user.setPhoto(IdenticonGeneratorService.generateByteImage(user.getName(),320,320));
        Set<Role> set = new HashSet<>();
        set.add(null);
        user.setRoles(set);
        user.setPassword(password);
        Role role = new Role(roleName);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        when(roleService.findOrCreateRole(roleName)).thenReturn(role);
        when(userDao.save(user)).thenReturn(user);
        assertEquals(userService.createUser(user.getEmail(), user.getPassword()), user);
    }

    @Test
    public void returnUserWhenUpdateUser() throws Exception {
        when(userDao.save(user)).thenReturn(user);
        assertEquals(userService.updateUser(user), user);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void invokeUserDaoWhenDeleteUser() throws Exception {
        userService.deleteUser(ID);
        verify(userDao, times(1)).delete(ID);
    }

    @Test
    public void invokeUserDaoWhenGetUserById() throws Exception {
        when(userDao.findOne(ID)).thenReturn(user);
        assertEquals(userService.getUserById(ID), user);
        verify(userDao, times(1)).findOne(ID);
    }

    @Test
    public void invokeUserDaoWhenGetAllUsers() throws Exception {
        when(userDao.findAll()).thenReturn(new ArrayList<>());
        userService.getAllUsers();
        verify(userDao, times(1)).findAll();
    }

    @Test
    public void invokeUserDaoWhenGetUserByEmail() throws Exception {
        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        assertEquals(userService.getUserByEmail(user.getEmail()), user);
        verify(userDao, times(1)).findByEmail(user.getEmail());
    }
}
