package net.greatstart.services;

import net.greatstart.dao.PasswordTokenDao;
import net.greatstart.model.PasswordResetToken;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@Transactional
public class SecurityService {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private PasswordTokenDao passwordTokenDao;

    @Autowired
    public SecurityService(UserDetailsService userDetailsService, AuthenticationManager authenticationManager,
                           PasswordTokenDao passwordTokenDao) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordTokenDao = passwordTokenDao;
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    public String validatePasswordResetToken(long id, String token) {
        PasswordResetToken passToken =
            passwordTokenDao.findByToken(token);
        if ((passToken == null) || (passToken.getUser()
            .getId() != id)) {
            return "invalid token";
        }

        if (passToken.getExpiryDate().isBefore(LocalDateTime.now()) || passToken.isUsed()) {
            return "token expired or has been already used";
        }

        User user = passToken.getUser();
        Authentication auth = new UsernamePasswordAuthenticationToken(
            user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        passToken.setUsed(true);
        return null;
    }
}
