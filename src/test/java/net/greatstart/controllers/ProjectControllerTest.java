package net.greatstart.controllers;

import net.greatstart.MapperHelper;
import net.greatstart.dto.DtoProject;
import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Project;
import net.greatstart.services.CategoryService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static net.greatstart.JsonConverter.convertObjectToJsonBytes;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private Principal principal;
    @InjectMocks
    private ProjectController controller;
    private MockMvc mockMvc;

    private static final long ID = 1L;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void getProjects() throws Exception {
        mockMvc.perform(get("/api/project"))
                .andExpect(status().isOk());
        verify(projectService).getAllProjects();
    }

    @Test
    public void getMyProjects() throws Exception {
        mockMvc.perform(get("/api/projects/my").principal(principal))
                .andExpect(status().isOk());
        verify(projectService).getAllProjectsOfCurrentUser();
    }

    @Test
    public void getMyProjectsNotLoggedInExpectBadRequest() throws Exception {
        mockMvc.perform(get("/api/project/my"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProjectById() throws Exception {
        DtoProject dtoProject = new DtoProject();
        dtoProject.setId(ID);
        when(projectService.getDtoProjectById(ID)).thenReturn(dtoProject);
        mockMvc.perform(get("/api/project/" + ID))
                .andExpect(status().isOk());
        verify(projectService).getDtoProjectById(ID);
    }

    @Test
    public void getNonExistingProjectByIdExpectNotFound() throws Exception {
        mockMvc.perform(get("/api/project/" + ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectNoRequestBody() throws Exception {
        mockMvc.perform(put("/api/project/" + ID))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateProjectWrongUserExpectForbidden() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        dtoProject.getOwner().setId(-1L);
        when(userService.getLoggedInUser())
                .thenReturn(MapperHelper.getTestUser());
        mockMvc.perform(put("/api/project/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject)))
                .andExpect(status().isForbidden());
        verify(userService).getLoggedInUser();
    }

    @Test
    public void updateProjectCorrectUserCouldNotSaveExpectNotFound() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        when(userService.getLoggedInUser())
                .thenReturn(MapperHelper.getTestUser());
        mockMvc.perform(put("/api/project/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject)))
                .andExpect(status().isNotFound());

        verify(userService).getLoggedInUser();
        verify(projectService).saveDtoProject(dtoProject);
    }

    @Test
    public void updateProjectCorrectUserExpectSuccess() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        when(userService.getLoggedInUser())
                .thenReturn(MapperHelper.getTestUser());
        when(projectService.saveDtoProject(dtoProject)).thenReturn(dtoProject);
        mockMvc.perform(put("/api/project/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject)))
                .andExpect(status().isOk());
        verify(userService).getLoggedInUser();
        verify(projectService).saveDtoProject(dtoProject);
    }

    @Test
    public void newValidProjectExpectSuccess() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        when(projectService.createNewProjectFromDto(dtoProject)).thenReturn(dtoProject);
        mockMvc.perform(post("/api/project/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject)))
                .andExpect(status().isOk());
        verify(projectService).createNewProjectFromDto(dtoProject);
    }

    @Test
    public void newProjectCannotSaveExpectNotFound() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        mockMvc.perform(post("/api/project/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject)))
                .andExpect(status().isNotFound());
        verify(projectService).createNewProjectFromDto(dtoProject);
    }

    @Test
    public void deleteExistingProjectExpectSuccess() throws Exception {
        Project project = MapperHelper.getTestProject();
        when(projectService.getProjectById(ID)).thenReturn(project);
        when(userService.getLoggedInUser()).thenReturn(project.getOwner());
        mockMvc.perform(delete("/api/project/" + ID))
                .andExpect(status().isOk());
        verify(userService).getLoggedInUser();
        verify(projectService).getProjectById(ID);
        verify(projectService).deleteProject(ID);
    }

    @Test
    public void deleteExistingProjectWrongUserExpectForbidden() throws Exception {
        Project project = MapperHelper.getTestProject();
        when(projectService.getProjectById(ID)).thenReturn(project);
        when(userService.getLoggedInUser()).thenReturn(MapperHelper.TEST_USER);
        mockMvc.perform(delete("/api/project/" + ID))
                .andExpect(status().isForbidden());
        verify(projectService).getProjectById(ID);
        verify(projectService, times(0)).deleteProject(ID);
    }

    @Test
    public void deleteNonExistingProjectExpectNotFound() throws Exception {
        when(projectService.getProjectById(ID)).thenReturn(null);
        when(principal.getName()).thenReturn(MapperHelper.TEST_USER_NAME);
        mockMvc.perform(delete("/api/project/" + ID)
                .principal(principal))
                .andExpect(status().isNotFound());
        verify(projectService).getProjectById(ID);
        verify(projectService, times(0)).deleteProject(ID);
    }

}