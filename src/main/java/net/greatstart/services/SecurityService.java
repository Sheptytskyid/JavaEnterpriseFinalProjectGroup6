package net.greatstart.services;

import net.greatstart.dao.PasswordTokenDao;
import net.greatstart.model.PasswordResetToken;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
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
import java.util.Locale;
import java.util.UUID;

@Service
@Transactional
public class SecurityService {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private PasswordTokenDao passwordTokenDao;
    private MessageSource messages;

    @Autowired
    public SecurityService(UserDetailsService userDetailsService, AuthenticationManager authenticationManager,
                           PasswordTokenDao passwordTokenDao, @Qualifier("messageSource") MessageSource messages) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordTokenDao = passwordTokenDao;
        this.messages = messages;
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

    public String validatePasswordResetToken(long userId, String token, Locale locale) {
        String result = null;
        PasswordResetToken passToken = passwordTokenDao.findByToken(token);
        if ((passToken == null) || (passToken.getUser().getId() != userId)) {
            result =  messages.getMessage("token.invalid", null, locale);
        } else if (passToken.getExpiryDate().isBefore(LocalDateTime.now()) || passToken.isUsed()) {
            result =  messages.getMessage("token.expired", null, locale);
        } else {
            authoriseToken(passToken);
        }
        return result;
    }

    public PasswordResetToken createPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken(token, user, LocalDateTime.now().plusHours(6), false);
        return passwordTokenDao.save(myToken);
    }

    public void expireToken(User user) {
        PasswordResetToken token = passwordTokenDao.findFirstByUserIdOrderByIdDesc(user.getId());
        token.setUsed(true);
        passwordTokenDao.save(token);
    }

    private void authoriseToken(PasswordResetToken passToken) {
        User user = passToken.getUser();
        Authentication auth = new UsernamePasswordAuthenticationToken(
            user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
