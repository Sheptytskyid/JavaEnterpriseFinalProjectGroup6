package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.CycleAvoidingMappingContext;
import net.greatstart.mappers.UserProfileMapper;
import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private UserProfileMapper userMapper;
    private UserDao userDao;
    private RoleService roleService;
    private CycleAvoidingMappingContext mappingContext = new CycleAvoidingMappingContext();

    @Autowired
    public UserService(UserProfileMapper userMapper, UserDao userDao, RoleService roleService) {
        this.userMapper = userMapper;
        this.userDao = userDao;
        this.roleService = roleService;
    }

    //ToDo: Rewrite method for create/delete User

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User createUser(String email, String password) {
        User user = new User();
        int i = email.indexOf('@');
        String name = email.substring(0, i);
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findOrCreateRole("ROLE_USER"));
        user.setRoles(roles);
        return createUser(user);
    }

    public DtoUserProfile updateUser(DtoUserProfile dtoUser, long id) {
        User currentUser = userDao.findOne(dtoUser.getId());
        if (currentUser != null && id == currentUser.getId()) {
            User entity = userMapper.fromDtoProfileToUser(dtoUser);
            entity.setPassword(currentUser.getPassword());
            userDao.save(entity);
            return userMapper.fromUserToDtoProfile(entity, mappingContext);
        }
        return null;

    }

    public void deleteUser(long id) {
        userDao.delete(id);
    }

    public DtoUserProfile getUserById(long id) {
        return userMapper.fromUserToDtoProfile(userDao.findOne(id), mappingContext);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    public User changeUserPassword(User user, String password) {
        user.setPassword(password);
        return userDao.save(user);
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
