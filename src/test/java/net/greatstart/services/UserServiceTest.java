package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final long ID = 1L;
    private static final String NAME = "Admin";
    private static final String EMAIL = "admin@example.com";
    private static final String PASSWORD = "1111";
    private static final String ROLE_NAME = "ROLE_USER";

    @Mock
    private UserDao userDao;
    @Mock
    private RoleService roleService;
    @InjectMocks
    private UserService userService;
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void shouldInvokeUserDaoWhenCreateUser() throws Exception {
        when(userDao.save(user)).thenReturn(user);
        assertEquals(userService.createUser(user), user);
    }


    @Test
    public void createUserByEmailAndPassword() throws Exception {
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Role role = new Role(ROLE_NAME);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        when(roleService.findOrCreateRole(ROLE_NAME)).thenReturn(role);
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
    public void changeUserPassword() throws Exception {
        when(userDao.save(user)).thenReturn(user);
        user = userService.changeUserPassword(user, PASSWORD);
        assertEquals(PASSWORD, user.getPassword());
        verify(userDao, times(1)).save(user);
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

    @Test
    public void getInitialsFromTwoNames() throws Exception {
        String initials = userService.getInitials("test", "User");
        assertEquals("T.U.", initials);
    }

    @Test
    public void getInitialsFromOneName() throws Exception {
        String initials = userService.getInitials("test", null);
        assertEquals("T", initials);
    }
}
