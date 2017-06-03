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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProjectRestControllerTest {
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
    private ProjectRestController controller;
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
        verify(projectService).getAllProjectsOfUser(null);
    }

    @Test
    public void getMyProjectsNotLoggedInExpectBadRequest() throws Exception {
        mockMvc.perform(get("/api/project/my"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProjectById() throws Exception {
        Project project = new Project();
        project.setId(ID);
        DtoProject dtoProject = new DtoProject();
        dtoProject.setId(ID);
        when(projectService.getProjectById(ID)).thenReturn(project);
        when(projectMapper.fromProjectToDto(project)).thenReturn(dtoProject);
        mockMvc.perform(get("/api/project/" + ID))
                .andExpect(status().isOk());
        verify(projectService).getProjectById(ID);
        verify(projectMapper).fromProjectToDto(project);
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
        Project project = MapperHelper.getTestProject();
        project.getOwner().setId(-1);
        when(userService.getUserByEmail(null))
                .thenReturn(MapperHelper.getTestUser());
        when(projectMapper.projectFromDto(dtoProject))
                .thenReturn(project);
        mockMvc.perform(put("/api/project/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject))
                .principal(principal))
                .andExpect(status().isForbidden());
        verify(userService).getUserByEmail(null);
        verify(projectMapper).projectFromDto(dtoProject);
    }

    @Test
    public void updateProjectCorrectUserCouldNotSaveExpectNotFound() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        Project project = MapperHelper.getTestProject();
        project.getOwner().setId(MapperHelper.getTestUser().getId());
        when(userService.getUserByEmail(null))
                .thenReturn(MapperHelper.getTestUser());
        when(projectMapper.projectFromDto(dtoProject))
                .thenReturn(project);
        mockMvc.perform(put("/api/project/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject))
                .principal(principal))
                .andExpect(status().isNotFound());

        verify(userService).getUserByEmail(null);
        verify(projectMapper).projectFromDto(dtoProject);
        verify(projectService).saveProject(project);
    }

    @Test
    public void updateProjectCorrectUserExpectSuccess() throws Exception {
        DtoProject dtoProject = MapperHelper.getTestDtoProject();
        Project project = MapperHelper.getTestProject();
        project.getOwner().setId(MapperHelper.getTestUser().getId());
        when(userService.getUserByEmail(null))
                .thenReturn(MapperHelper.getTestUser());
        when(projectMapper.projectFromDto(dtoProject))
                .thenReturn(project);
        when(projectService.saveProject(project)).thenReturn(project);
        mockMvc.perform(put("/api/project/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoProject))
                .principal(principal))
                .andExpect(status().isOk());
        verify(userService).getUserByEmail(null);
        verify(projectMapper).projectFromDto(dtoProject);
        verify(projectService).saveProject(project);
    }

    @Test
    public void newProject() throws Exception {
    }

    @Test
    public void deleteProject() throws Exception {
    }

}