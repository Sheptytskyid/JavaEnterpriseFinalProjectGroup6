package net.greatstart.services;

import net.greatstart.MapperHelper;
import net.greatstart.dao.UserDao;
import net.greatstart.dto.DtoUser;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static junit.framework.TestCase.assertNull;
import static net.greatstart.MapperHelper.getFullTestUser;
import static net.greatstart.MapperHelper.getTestDtoUserProfile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    @Mock
    private UserProfileMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SecurityService securityService;
    @InjectMocks
    private UserService userService;
    private User user;
    private DtoUserProfile dtoUser;

    @Before
    public void setUp() {
        user = getFullTestUser();
        dtoUser = getTestDtoUserProfile();
    }

    @Test
    public void shouldInvokeUserDaoWhenCreateUser() throws Exception {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setEmail(EMAIL);
        dtoUser.setPassword(PASSWORD);
        dtoUser.setConfirmPassword(PASSWORD);
        userService.setSecurityService(securityService);
        when(userDao.save(any(User.class))).thenReturn(user);
        assertNotNull(userService.createUser(dtoUser));
    }

    @Test
    public void createUserByEmailAndPassword() throws Exception {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setEmail(EMAIL);
        dtoUser.setPassword(PASSWORD);
        dtoUser.setConfirmPassword(PASSWORD);
        User user = new User();

        userService.setSecurityService(securityService);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(roleService.findOrCreateRole("ROLE_USER")).thenReturn(new Role());
        when(userDao.save(any(User.class))).thenReturn(user);
        assertNotNull(userService.createUser(dtoUser));
        verify(passwordEncoder).encode(PASSWORD);
        verify(roleService).findOrCreateRole("ROLE_USER");
        verify(userDao).save(any(User.class));
    }

    @Test
    public void createUserNullFieldsExpectNull() throws Exception {
        DtoUser dtoUser = new DtoUser();
        assertNull(userService.createUser(dtoUser));
    }

    @Test
    public void returnUserWhenUpdateUser() throws Exception {
        when(userDao.findOne(user.getId())).thenReturn(user);
        when(userMapper.fromDtoProfileToUser(dtoUser)).thenReturn(user);
        when(userDao.save(user)).thenReturn(user);
        when(userMapper.fromUserToDtoProfile(user)).thenReturn(dtoUser);
        assertEquals(dtoUser, userService.updateUser(dtoUser, dtoUser.getId()));
        verify(userDao, times(1)).findOne(dtoUser.getId());
        verify(userMapper, times(1)).fromDtoProfileToUser(dtoUser);
        verify(userDao, times(1)).save(user);
        verify(userMapper, times(1)).fromUserToDtoProfile(user);
        verifyNoMoreInteractions(userDao);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    public void returnNullWhenUpdateUserWithInvalidId() throws Exception {
        when(userDao.findOne(user.getId())).thenReturn(null);
        assertEquals(null, userService.updateUser(dtoUser, dtoUser.getId()));
        verify(userDao, times(1)).findOne(dtoUser.getId());
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
        when(userMapper.fromUserToDtoProfile(user)).thenReturn(dtoUser);
        when(userDao.findOne(ID)).thenReturn(user);
        assertEquals(userService.getDtoUserProfileById(ID), dtoUser);
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
    public void invokeUserDaoWhenGetUser() throws Exception {
        //init
        when(userDao.findOne(1L)).thenReturn(user);
        //use & check
        assertEquals(user, userService.getUser(1L));
        verify(userDao, times(1)).findOne(1L);
        verifyNoMoreInteractions(userDao);
    }

    @Test
    public void invokeUserDaoWhenGetUserByEmail() throws Exception {
        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        assertEquals(userService.getUserByEmail(user.getEmail()), user);
        verify(userDao, times(1)).findByEmail(user.getEmail());
    }

    @Test
    public void getLoggedInUser() throws Exception {
        User testUser = MapperHelper.getFullTestUser();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(testUser.getEmail(), testUser.getPassword()));
        userService.getLoggedInUser();
        verify(userDao).findByEmail(testUser.getEmail());
    }

}
