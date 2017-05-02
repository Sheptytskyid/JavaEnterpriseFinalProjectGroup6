package net.greatstart.controllers;

import net.greatstart.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class LoginControllerTest {
    LoginController controller = new LoginController();
    private MockMvc mvc;

    @Before
    public void init() {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void showLoginForm() throws Exception {
        mvc.perform(get("/user/login")).andExpect(view().name("login/login"));
    }

}