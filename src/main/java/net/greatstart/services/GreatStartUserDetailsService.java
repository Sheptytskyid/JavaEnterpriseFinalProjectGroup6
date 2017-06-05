package net.greatstart.services;

import net.greatstart.dao.UserDao;
import net.greatstart.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService}.
 */

@Service
public class GreatStartUserDetailsService implements UserDetailsService {

    private UserService userService;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public GreatStartUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        net.greatstart.model.User user = userDao.findByEmail(email);
        if (user != null) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Role role: user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User " + email + " not found.");
    }
}
