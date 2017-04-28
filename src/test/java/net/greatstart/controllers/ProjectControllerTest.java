package net.greatstart.controllers;

import net.greatstart.Main;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@WebAppConfiguration
public class ProjectControllerTest {
    @Mock
    ProjectService projectService;
    @Mock
    UserService userService;
    private ProjectController controller;
    private MockMvc mockMvc;

    private final String REDIRECT_TO_PROJECTS = "redirect:/project/";
    private final String TEST_PROJECT_NAME = "New project";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ProjectController(projectService, userService);
        mockMvc = standaloneSetup(controller)
                .build();
    }

    @Test
    public void showProjects() throws Exception {
        mockMvc.perform(get("/project/")).andExpect(view().name("project/projects"));
        verify(projectService).getAllProjects();
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
                .param("desc.name", TEST_PROJECT_NAME))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService).createProject(project);
    }

    // empty accepted
    @Test
    public void addEmptyProject() throws Exception {
        mockMvc.perform(post("/project/new")).andExpect(view().name(REDIRECT_TO_PROJECTS));
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
        verify(projectService).updateProject(project);
    }

    @Test
    public void deleteProject() throws Exception {
        mockMvc.perform(get("/project/1/delete"))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService).deleteProject(1);
    }

}