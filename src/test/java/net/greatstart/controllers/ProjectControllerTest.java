package net.greatstart.controllers;

import net.greatstart.model.Investment;
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
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
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

    @Test(timeout = 2000)
    public void showProject() throws Exception {
        Investment investment1 = new Investment();
        investment1.setSum(new BigDecimal(123));
        Investment investment2 = new Investment();
        investment2.setSum(new BigDecimal(456));
        List<Investment> investments = Arrays.asList(investment1, investment2);
        Project project = new Project();
        project.setInvestments(investments);
        when(projectService.getProjectById(1L)).thenReturn(project);
        mockMvc.perform(get("/project/1"))
                .andExpect(view().name("project/project_page"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("investedAmount", new BigDecimal(579)));
    }

    @Test(timeout = 2000)
    public void showProjectsShouldReturnViewAndInvokeServiceMethod() throws Exception {
        mockMvc.perform(get("/project/"))
                .andExpect(view().name("project/projects"));
        verify(projectService).getAllProjects();
    }

    @Test(timeout = 2000)
    public void showMyProjectsShouldReturnViewAndInvokeServiceMethod() throws Exception {
        mockMvc.perform(get("/project/my").principal(principal))
                .andExpect(view().name("project/projects"));
        verify(projectService).getAllProjectsOfUser(USERNAME);
    }

    @Test(timeout = 2000)
    public void getAddProjectFormShouldReturnView() throws Exception {
        mockMvc.perform(get("/project/new"))
                .andExpect(view().name("project/add_project"));
    }

    @Test(timeout = 2000)
    public void addProjectWithName() throws Exception {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setName(TEST_PROJECT_NAME);
        project.setDesc(desc);
        mockMvc.perform(post("/project/new")
                .principal(principal)
                .param("desc.name", TEST_PROJECT_NAME))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService, times(1)).saveProject(project);
    }

    @Test(timeout = 2000)
    public void addEmptyProject() throws Exception {
        mockMvc.perform(post("/project/new")
                .principal(principal))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService, times(0)).saveProject(null);
    }

    @Test(timeout = 2000)
    public void addProjectWithWrongPrice() throws Exception {
        mockMvc.perform(post("/project/new")
                .param("desc.cost", "wrong"))
                .andExpect(view().name("project/add_project"));
        verify(projectService, times(0)).saveProject(null);
    }

    @Test(timeout = 2000)
    public void getUpdateProjectFormWithCorrectId() throws Exception {
        mockMvc.perform(get("/project/1/update"))
                .andExpect(view().name("project/update_project"));
    }

    @Test(timeout = 2000)
    public void getUpdateProjectFormWithWrongId() throws Exception {
        mockMvc.perform(get("/project/-1/update"))
                .andExpect(view().name("project/projects"));
    }

    @Test(timeout = 2000)
    public void updateProjectShouldReturnViewAndInvokeServiceMethod() throws Exception {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setName(TEST_PROJECT_NAME);
        project.setDesc(desc);
        mockMvc.perform(post("/project/2/update")
                .param("desc.name", TEST_PROJECT_NAME))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService, times(1)).saveProject(project);
    }

    @Test(timeout = 2000)
    public void deleteProjectShouldReturnViewAndInvokeServiceMethod() throws Exception {
        mockMvc.perform(get("/project/1/delete"))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService, times(1)).deleteProject(1);
    }

    @Test(timeout = 2000)
    public void downloadPhoto() throws Exception {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        byte[] photo = {0, 1, 1, 1, 0};
        desc.setPhoto(photo);
        project.setDesc(desc);
        when(projectService.getProjectById(1L)).thenReturn(project);
        mockMvc.perform(get("/project/photo/1"))
                .andExpect(content().bytes(photo));

    }
}
