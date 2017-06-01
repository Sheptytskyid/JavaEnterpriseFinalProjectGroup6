package net.greatstart.controllers;

import net.greatstart.model.PasswordResetToken;
import net.greatstart.model.User;
import net.greatstart.services.MailService;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class PasswordResetControllerTest {

    private static final String VALID_PASSWORD = "validPassword";
    private static final String VALID_EMAIL = "valid@email.com";
    private static final String LOCALHOST = "http://localhost:8080/";
    private static final Locale LOCALE = new Locale("en");
    private static final String TOKEN_VALUE = "1";

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MailService mailService;
    @Mock
    private UserService userService;
    @Mock
    private SecurityService securityService;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private PasswordResetController controller;
    private MockMvc mvc;
    private User user;
    private PasswordResetToken token;

    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(controller).build();
        user = new User();
        token = new PasswordResetToken();
    }

    @Test
    public void processNonExistingEmailShouldReturnBadRequestStatus() throws Exception {
        mvc.perform(get("/user/resetPassword").param("email", VALID_EMAIL)).andExpect(status().isNotFound());
    }

    @Test
    public void processValidEmailShouldReturnBadRequestStatusWhenFailToSendMail() throws Exception {
        token.setToken(TOKEN_VALUE);
        when(userService.getUserByEmail(VALID_EMAIL)).thenReturn(user);
        when(securityService.createPasswordResetToken(user)).thenReturn(token);
        when(mailService.sendResetTokenEmail(request, LOCALE, TOKEN_VALUE, user)).thenReturn(false);
        mvc.perform(get("/user/resetPassword").param("email", VALID_EMAIL).header("referer", LOCALHOST).locale
            (LOCALE)).andExpect
            (status().isInternalServerError());
    }

    @Test
    public void processValidEmailShouldReturnOkStatusWhenSucceedToSendMail() throws Exception {
        token.setToken(TOKEN_VALUE);
        when(userService.getUserByEmail(VALID_EMAIL)).thenReturn(user);
        when(securityService.createPasswordResetToken(user)).thenReturn(token);
        when(mailService.sendResetTokenEmail(request, LOCALE, TOKEN_VALUE, user)).thenReturn(true);
        mvc.perform(get("/user/resetPassword").param("email", VALID_EMAIL).header("referer", LOCALHOST).locale(LOCALE)).andExpect
            (status().isInternalServerError());
    }

    @Test
    public void savePasswordShouldReturnBadRequestStatusWhenTokenValidationFails() throws Exception {
        when(securityService.validatePasswordResetToken(TOKEN_VALUE, LOCALE)).thenReturn("fail");
        mvc.perform(post("/user/resetPassword").param("token", TOKEN_VALUE).content(VALID_PASSWORD)
            .locale(LOCALE)).andExpect(status().isOk());
    }

    @Test
    public void savePasswordShouldReturnOkStatusWhenTokenValidationSucceeds() throws Exception {
        when(securityService.getUserByToken(TOKEN_VALUE)).thenReturn(user);
        when(passwordEncoder.encode(VALID_PASSWORD)).thenReturn(VALID_PASSWORD);
        when(userService.changeUserPassword(user, VALID_PASSWORD)).thenReturn(user);
        mvc.perform(post("/user/resetPassword").param("token", TOKEN_VALUE).content(VALID_PASSWORD)
            .locale(LOCALE)).andExpect(status().isOk());
        verify(securityService, times(1)).expireToken(user);
        verify(userService, times(1)).changeUserPassword(user, VALID_PASSWORD);
    }
}
