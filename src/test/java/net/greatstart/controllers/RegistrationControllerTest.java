package net.greatstart.controllers;

import net.greatstart.Main;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class RegistrationControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SecurityService securityService;

    private RegistrationController controller =
            new RegistrationController(passwordEncoder, userService, securityService);
    private MockMvc mvc;

    @Before
    public void init() {
        mvc = standaloneSetup(controller).build();
    }


    @Test
    public void register() throws Exception {
        mvc.perform(get("/user/register"))
                .andExpect(view().name("login/registration"));
    }


}