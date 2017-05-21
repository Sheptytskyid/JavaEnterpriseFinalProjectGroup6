package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.services.converters.ProjectConverterService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {
    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    @Mock
    private ProjectConverterService projectConverter;
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
        DtoProject dtoProject = new DtoProject();
        dtoProject.setName(TEST_PROJECT_NAME);
        when(projectConverter.newProjectFromDto(dtoProject, null)).thenReturn(project);
        mockMvc.perform(post("/project/new")
                .principal(principal)
                .param("name", TEST_PROJECT_NAME))
                .andExpect(view().name(REDIRECT_TO_PROJECTS));
        verify(projectService, times(1)).saveProject(project);
    }

    @Test(timeout = 2000)
    public void addEmptyProject() throws Exception {
        mockMvc.perform(post("/project/new")
                .principal(principal))
                .andExpect(view().name("project/add_project"));
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
        DtoProject dtoProject = new DtoProject();
        dtoProject.setDescription(TEST_PROJECT_NAME);
        Project project = new Project();
        when(projectService.getProjectById(2)).thenReturn(project);
        mockMvc.perform(post("/project/2/update")
                .param("name", TEST_PROJECT_NAME))
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
    public void downloadImage() throws Exception {
        DtoProject project = new DtoProject();
        byte[] photo = {90, 81, 81, 81, 90};
        project.setImage(photo);
        Project emptyProject = new Project();
        when(projectService.getProjectById(1L)).thenReturn(emptyProject);
        when(projectConverter.fromProjectToDto(emptyProject)).thenReturn(project);
        mockMvc.perform(get("/project/1/image"))
                .andExpect(content().bytes(photo));

    }
}
