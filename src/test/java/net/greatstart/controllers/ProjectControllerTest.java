package net.greatstart.controllers;

import net.greatstart.Main;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Main.class)
public class ProjectControllerTest {
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @InjectMocks
    private ProjectController controller;
    private MockMvc mockMvc;

    private final String REDIRECT_TO_PROJECTS = "redirect:/project/";
    private final String TEST_PROJECT_NAME = "New project";
    private final String USERNAME = "";
    private Principal principal = () -> USERNAME;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void showProjects() throws Exception {
        mockMvc.perform(get("/project/")).andExpect(view().name("project/projects"));
        verify(projectService).getAllProjects();
    }

    @Test
    public void showMyProjects() throws Exception {
        mockMvc.perform(get("/project/my").principal(principal)).andExpect(view().name("project/projects"));
        verify(projectService).getAllProjectsOfUser(USERNAME);
    }

    @Test
    public void getAddProjectForm() throws Exception {
        mockMvc.perform(get("/project/new")).andExpect(view().name("project/add_project"));
    }

    @Test
    public void addProjectWithName() throws Exception {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setName(TEST_PROJECT_NAME);
        project.setDesc(desc);
        mockMvc.perform(post("/project/new")
                .principal(principal)
                .param("desc.name", TEST_PROJECT_NAME))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService).saveProject(project);
    }

    @Test
    public void addEmptyProject() throws Exception {
        mockMvc.perform(post("/project/new")
                .principal(principal))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
    }


    @Test
    public void addProjectWithWrongPrice() throws Exception {
        mockMvc.perform(post("/project/new")
                .param("desc.cost", "wrong"))
                .andExpect(view().name("project/add_project"));
    }

    @Test
    public void getUpdateProjectFormWithCorrectId() throws Exception {
        mockMvc.perform(get("/project/1/update")).andExpect(view().name("project/update_project"));
    }

    @Test
    public void getUpdateProjectFormWithWrongId() throws Exception {
        mockMvc.perform(get("/project/-1/update")).andExpect(view().name("project/projects"));
    }

    @Test
    public void updateProject() throws Exception {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setName(TEST_PROJECT_NAME);
        project.setDesc(desc);
        mockMvc.perform(post("/project/2/update")
                .param("desc.name", TEST_PROJECT_NAME))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService).saveProject(project);
    }

    @Test
    public void deleteProject() throws Exception {
        mockMvc.perform(get("/project/1/delete"))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService).deleteProject(1);
    }

}