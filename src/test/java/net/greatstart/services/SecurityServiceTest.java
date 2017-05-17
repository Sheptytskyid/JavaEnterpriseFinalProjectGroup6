package net.greatstart.services;

import net.greatstart.dao.PasswordTokenDao;
import net.greatstart.model.PasswordResetToken;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {

    private static final String NAME = "name";
    private static final String PASS = "pass";
    private static final String TOKEN_VALUE = "1";
    private static final long ID = 1L;
    private static final Locale LOCALE = new Locale("en");
    private static final String MESSAGE = "message";

    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetails userDetails;
    @Mock
    private PasswordTokenDao passwordTokenDao;
    @Mock
    private MessageSource messages;
    @InjectMocks
    private SecurityService securityService;
    @Captor
    private ArgumentCaptor<PasswordResetToken> captor;
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void autoLogin() throws Exception {
        Set authorities = new HashSet();
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, PASS, authorities);
        when(userDetailsService.loadUserByUsername(NAME)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(authorities);
        securityService.autoLogin(NAME, PASS);
        verify(userDetailsService, times(1)).loadUserByUsername(NAME);
        verify(authenticationManager, times(1)).authenticate(authenticationToken);
    }

    @Test
    public void validateNonExistingPasswordResetTokenShouldReturnMessage() throws Exception {
        when(messages.getMessage("token.invalid", null, LOCALE)).thenReturn(MESSAGE);
        String result = securityService.validatePasswordResetToken(ID, TOKEN_VALUE, LOCALE);
        verify(passwordTokenDao, times(1)).findByToken(TOKEN_VALUE);
        assertEquals(MESSAGE, result);
    }

    @Test
    public void validateValidPasswordResetTokenShouldReturnNull() throws Exception {
        PasswordResetToken token = createToken();
        token.setExpiryDate(LocalDateTime.now().plusMinutes(1));
        when(passwordTokenDao.findByToken(TOKEN_VALUE)).thenReturn(token);
        String result = securityService.validatePasswordResetToken(ID, TOKEN_VALUE, LOCALE);
        verify(passwordTokenDao, times(1)).findByToken(TOKEN_VALUE);
        assertNull(result);
    }

    @Test
    public void validateExpiredPasswordResetTokenShouldReturnMessage() throws Exception {
        PasswordResetToken token = createToken();
        token.setExpiryDate(LocalDateTime.now().minusMinutes(1));
        when(passwordTokenDao.findByToken(TOKEN_VALUE)).thenReturn(token);
        when(messages.getMessage("token.expired", null, LOCALE)).thenReturn(MESSAGE);
        String result = securityService.validatePasswordResetToken(ID, TOKEN_VALUE, LOCALE);
        verify(passwordTokenDao, times(1)).findByToken(TOKEN_VALUE);
        assertEquals(MESSAGE, result);
    }

    @Test
    public void createPasswordResetTokenSavesToken() {
        securityService.createPasswordResetToken(user);
        verify(passwordTokenDao,times(1)).save(captor.capture());
        assertEquals(user, captor.getValue().getUser());
        assertNotNull(captor.getValue().getToken());
    }

    private PasswordResetToken createToken() {
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(TOKEN_VALUE);
        user.setId(ID);
        token.setUser(user);
        return token;
    }
}
