package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.PasswordResetToken;
import net.greatstart.model.User;
import net.greatstart.services.MailService;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.PasswordValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class PasswordResetControllerTest {

    private static final String VALID_PASSWORD = "validPassword";
    private static final String INVALID_EMAIL = "invalid";
    private static final String VALID_EMAIL = "valid@email.com";
    private static final String LOCALHOST = "http://localhost:8080/";
    private static final Locale LOCALE = new Locale("en");
    private static final String TOKEN_VALUE = "1";
    private static final String EMAIL_SENT = "email.sent";
    private static final String EMAIL_ERROR = "email.error";
    private static final String ID = "1";
    private static final String RESULT = "result";
    private static final String LOGIN_PAGE = "login/login";
    private static final String MESSAGE = "message";

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MessageSource messages;
    @Mock
    private MailService mailService;
    @Mock
    private UserService userService;
    @Mock
    private SecurityService securityService;
    @Mock
    private PasswordValidationService validationService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private Authentication authentication;
    @Mock
    private SecurityContext securityContext;
    @InjectMocks
    private PasswordResetController controller;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void showForgotPassForm() throws Exception {
        mvc.perform(get("/user/resetPassword"))
            .andExpect(view().name("user/forgotPassword"))
            .andExpect(model().attribute("user", new DtoUser()));
    }

    @Test
    public void processInvalidEmailShouldReturnModelWithErrors() throws Exception {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setEmail(INVALID_EMAIL);
        mvc.perform(post("/user/resetPassword").flashAttr("user", dtoUser))
            .andExpect(view().name("user/forgotPassword"))
            .andExpect(model().attributeHasFieldErrors("user", "email"));
    }

    @Test
    public void processNonExistingEmailShouldReturnModelWithMessage() throws Exception {
        DtoUser dtoUser = getValidDtoUser();
        when(userService.getUserByEmail(dtoUser.getEmail())).thenReturn(null);
        when(request.getLocale()).thenReturn(LOCALE);
        when(messages.getMessage("user.notFound", null, LOCALE)).thenReturn("test");
        mvc.perform(post("/user/resetPassword").flashAttr("user", dtoUser))
            .andExpect(view().name("user/forgotPassword"))
            .andExpect(model().attributeExists("message"));
    }

    @Test
    public void processValidExistingEmailWhenSendingFailsShouldReturnModelWithMessage() throws Exception {
        DtoUser dtoUser = prepareProcessValidExistingEmailTest(false);
        assertTrue(controller.processEmail(request, dtoUser, new BindException(dtoUser, "user"))
                            .getModel()
                            .get("message")
                            .equals(EMAIL_ERROR));

    }

    @Test
    public void processValidExistingEmailWhenSendingSucceedsShouldReturnModelWithMessage() throws Exception {
        DtoUser dtoUser = prepareProcessValidExistingEmailTest(true);
        assertTrue(controller.processEmail(request, dtoUser, new BindException(dtoUser, "user"))
                            .getModel()
                            .get("message")
                            .equals(EMAIL_SENT));

    }

    @Test
    public void validateInvalidPassTokenShouldRedirect() throws Exception {
        mvc.perform(get("/user/validateToken").param("id", ID).param("token", TOKEN_VALUE))
            .andExpect(view().name("redirect:/user/changePassword"));
    }

    @Test
    public void validateValidPassTokenShouldOpenLoginPage() throws Exception {
        when(securityService.validatePasswordResetToken(Long.valueOf(ID), TOKEN_VALUE, LOCALE)).thenReturn(RESULT);
        mvc.perform(get("/user/validateToken").param("id", ID).param("token", TOKEN_VALUE))
            .andExpect(view().name(LOGIN_PAGE))
            .andExpect(model().attributeExists(MESSAGE));
    }

    @Test
    public void showChangePasswordForm() throws Exception {
        mvc.perform(get("/user/changePassword"))
            .andExpect(view().name("user/updatePassword"))
            .andExpect(model().attribute("user", new DtoUser()));
    }

    @Test
    public void saveInvalidPasswordShouldRedirectBackToForm() throws Exception {
        DtoUser dtoUser = new DtoUser();
        mvc.perform(post("/user/changePassword").flashAttr("user", dtoUser))
            .andExpect(view().name("user/updatePassword"))
            .andExpect(model().attributeHasFieldErrors("user", "password"));
    }

    @Test
    public void saveValidPasswordShouldChangeUserPassword() throws Exception {
        DtoUser dtoUser = getValidDtoUser();
        User user = new User();
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        when(passwordEncoder.encode(dtoUser.getPassword())).thenReturn(VALID_PASSWORD);
        when(messages.getMessage("message.resetPassword.success", null, LOCALE)).thenReturn(RESULT);
        mvc.perform(post("/user/changePassword").flashAttr("user", dtoUser))
            .andExpect(view().name(LOGIN_PAGE))
            .andExpect(model().attribute(MESSAGE, RESULT));
        verify(userService, times(1)).changeUserPassword(user, VALID_PASSWORD);

    }

    private DtoUser getValidDtoUser() {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setEmail(VALID_EMAIL);
        dtoUser.setPassword(VALID_PASSWORD);
        dtoUser.setConfirmPassword(VALID_PASSWORD);
        return dtoUser;
    }

    private DtoUser prepareProcessValidExistingEmailTest(boolean resultOfSendEmail ) {
        DtoUser dtoUser = getValidDtoUser();
        User user = new User();
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(TOKEN_VALUE);
        when(userService.getUserByEmail(dtoUser.getEmail())).thenReturn(user);
        when(securityService.createPasswordResetTokenForUser(user)).thenReturn(token);
        when(request.getHeader("origin")).thenReturn(LOCALHOST);
        when(request.getLocale()).thenReturn(LOCALE);
        when(mailService.sendResetTokenEmail(LOCALHOST, LOCALE, TOKEN_VALUE, user)).thenReturn(resultOfSendEmail);
        when(messages.getMessage(EMAIL_SENT, null, LOCALE)).thenReturn(EMAIL_SENT);
        when(messages.getMessage(EMAIL_ERROR, null, LOCALE)).thenReturn(EMAIL_ERROR);
        return dtoUser;
    }
}