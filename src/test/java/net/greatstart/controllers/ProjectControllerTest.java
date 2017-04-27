package net.greatstart.controllers;

import net.greatstart.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@WebAppConfiguration
public class ProjectControllerTest {
    @Autowired
    private ProjectController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(controller)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
    }

    @Test
    public void showProjects() throws Exception {
        mockMvc.perform(get("/projects")).andExpect(view().name("projects"));
    }

    @Test
    public void getAddProjectForm() throws Exception {
        mockMvc.perform(get("/add_project")).andExpect(view().name("add_project"));
    }

    @Test
    public void addProject() throws Exception {
    }

    @Test
    public void getUpdateProjectForm() throws Exception {
        mockMvc.perform(get("/update_project?id=1")).andExpect(view().name("update_project"));
        mockMvc.perform(get("/update_project?id=-1")).andExpect(view().name("projects"));
    }

    @Test
    public void updateProject() throws Exception {
    }

    @Test
    public void deleteProject() throws Exception {
    }

}