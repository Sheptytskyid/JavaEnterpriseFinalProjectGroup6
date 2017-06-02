package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.UserProfileMapper;
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

import static net.greatstart.MapperHelper.getFullTestDtoUserProfile;
import static net.greatstart.MapperHelper.getFullTestUser;
import static net.greatstart.MapperHelper.CONTEXT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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
    @Mock
    private UserProfileMapper userMapper;
    @InjectMocks
    private UserService userService;
    private User user;
    private DtoUserProfile dtoUser;

    @Before
    public void setUp() {
        user = getFullTestUser();
        dtoUser = getFullTestDtoUserProfile();
    }


    @Test
    public void shouldInvokeUserDaoWhenCreateUser() throws Exception {
        when(userDao.save(user)).thenReturn(user);
        assertEquals(userService.createUser(user), user);
    }


    @Test
    public void createUserByEmailAndPassword() throws Exception {
        user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Role role = new Role(ROLE_NAME);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        when(roleService.findOrCreateRole(ROLE_NAME)).thenReturn(role);
        when(userDao.save(user)).thenReturn(user);
        assertEquals(user, userService.createUser(user.getEmail(), user.getPassword()));
    }

    @Test
    public void returnUserWhenUpdateUser() throws Exception {
        when(userDao.findOne(user.getId())).thenReturn(user);
        when(userMapper.fromDtoProfileToUser(dtoUser)).thenReturn(user);
        when(userDao.save(user)).thenReturn(user);
        when(userMapper.fromUserToDtoProfile(user, CONTEXT)).thenReturn(dtoUser);
        assertEquals(dtoUser, userService.updateUser(dtoUser, dtoUser.getId()));
        verify(userDao, times(1)).findOne(dtoUser.getId());
        verify(userMapper, times(1)).fromDtoProfileToUser(dtoUser);
        verify(userDao, times(1)).save(user);
        verify(userMapper, times(1)).fromUserToDtoProfile(user, CONTEXT);
        verifyNoMoreInteractions(userDao);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    public void invokeUserDaoWhenDeleteUser() throws Exception {
        userService.deleteUser(ID);
        verify(userDao, times(1)).delete(ID);
    }

    @Test
    public void invokeUserDaoWhenGetUserById() throws Exception {
        when(userMapper.fromUserToDtoProfile(user, CONTEXT)).thenReturn(dtoUser);
        when(userDao.findOne(ID)).thenReturn(user);
        assertEquals(dtoUser, userService.getDtoUserProfileById(ID));
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

}
