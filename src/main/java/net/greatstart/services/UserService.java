package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.dto.DtoUser;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.UserProfileMapper;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Business logic layer for {@link net.greatstart.model.User}.
 */

@Service
@Transactional
public class UserService {

    private UserProfileMapper userMapper;
    private UserDao userDao;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private SecurityService securityService;

    @Autowired
    public UserService(UserProfileMapper userMapper,
                       UserDao userDao,
                       RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getLoggedInUser() {
        return getUserByEmail(getCurrentUsername());
    }

    public DtoUserProfile getCurrentDtoUserProfile() {
        return userMapper.fromUserToDtoProfile(getLoggedInUser());
    }

    //ToDo: Rewrite method for create/delete User

    public User createUser(DtoUser newUser) {
        String email = newUser.getEmail();
        String password = newUser.getPassword();
        if (getUserByEmail(email) == null && password.equals(newUser.getConfirmPassword())) {
            User user = new User();
            int i = email.indexOf('@');
            String name = email.substring(0, i);
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findOrCreateRole("ROLE_USER"));
            user.setRoles(roles);
            user = userDao.save(user);
            securityService.autoLogin(user.getEmail(), user.getPassword());
            return user;
        }
        return null;
    }


    public DtoUserProfile updateUser(DtoUserProfile dtoUser, long id) {
        User currentUser = userDao.findOne(dtoUser.getId());
        if (currentUser != null && id == currentUser.getId()) {
            User entity = userMapper.fromDtoProfileToUser(dtoUser);
            entity.setPassword(currentUser.getPassword());
            userDao.save(entity);
            return userMapper.fromUserToDtoProfile(entity);
        }
        return null;

    }

    public void deleteUser(long id) {
        userDao.delete(id);
    }

    public DtoUserProfile getDtoUserProfileById(long id) {
        return userMapper.fromUserToDtoProfile(userDao.findOne(id));
    }

    public User getUser(long id) {
        return userDao.findOne(id);
    }

    public User changeUserPassword(User user, String password) {
        user.setPassword(password);
        return userDao.save(user);
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
