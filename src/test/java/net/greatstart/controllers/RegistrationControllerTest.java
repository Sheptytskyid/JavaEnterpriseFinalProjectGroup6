package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.UserValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    private static final String EMAIL = "a@example.com";
    private static final String PASS = "pass";

    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SecurityService securityService;
    @Mock
    private UserValidationService userValidationService;
    @Mock
    private Errors errors;

    @InjectMocks
    private RegistrationController controller;
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = standaloneSetup(controller).build();
    }

    @Test(timeout = 2000)
    public void registerShouldReturnView() throws Exception {
        mvc.perform(get("/user/register")).andExpect(view().name("login/registration"));
    }

    @Test(timeout = 2000)
    public void processRegistrationWrongUser() throws Exception {
        mvc.perform(post("/user/register")).andExpect(view().name("login/registration"));
    }

    @Test(timeout = 2000)
    public void processRegistration() {
        DtoUser user = new DtoUser();
        user.setEmail(EMAIL);
        user.setPassword(PASS);
        ModelAndView modelAndView = controller.processRegistration(user, errors);
        verify(userValidationService, times(1)).validate(user, errors, userService);
        verify(userService, times(1)).createUser(user.getEmail(), passwordEncoder.encode(user.getPassword()));
        verify(securityService, times(
                1)).autoLogin(user.getEmail(), user.getPassword());
        assertEquals(modelAndView.getViewName(), "redirect:/");
    }

}
