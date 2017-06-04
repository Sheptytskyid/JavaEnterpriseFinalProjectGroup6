package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.dto.DtoProject;
import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static net.greatstart.MapperHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @Mock
    private ProjectDao projectDao;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private UserService userService;
    @InjectMocks
    private ProjectService projectService;
    private Project project;
    private DtoProject dtoProject;

    @Before
    public void setUp() {
        project = getTestProject(TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        dtoProject = getTestDtoProject(TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
    }

    @Test
    public void invokeProjectDaoWhenSaveProject() throws Exception {
        projectService.saveProject(project);
        verify(projectDao, times(1)).save(project);
    }

    @Test
    public void invokeProjectDaoWhenDeleteProject() throws Exception {
        projectService.deleteProject(1L);
        verify(projectDao, times(1)).delete(1L);
    }

    @Test
    public void returnProjectWhenGetProjectById() throws Exception {
        when(projectDao.findOne(1L)).thenReturn(project);
        assertEquals(projectService.getProjectById(1L), project);
    }

    @Test
    public void invokeProjectDaoWhenGetAllProjects() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(projectDao.findAll()).thenReturn(projects);
        assertEquals(projectService.getAllProjects(), projects);
    }

    @Test
    public void invokeDaoWhenGetNProjects() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        List<Project> projects = new ArrayList<>();
        Page<Project> page = new PageImpl<Project>(projects);
        when(projectDao.findAll(pageable)).thenReturn(page);
        assertEquals(projectService.getNProjects(10), projects);
    }

    @Test
    public void invokeDaoWhenGetAllProjectsOfUser() {
        //init
        String email = "test@test.ua";
        User user = new User();
        when(userService.getLoggedInUser()).thenReturn(user);
        //use
        projectService.getAllProjectsOfCurrentUser();
        //check
        verify(projectDao, times(1)).findByOwner(user);
    }

    @Test
    public void invokeDaoWhenGetDtoProjectById() throws Exception {
        //init
        when(projectDao.findOne(1L)).thenReturn(project);
        when(projectMapper.fromProjectToDto(project)).thenReturn(dtoProject);
        //use
        projectService.getDtoProjectById(1L);
        //check
        verify(projectDao, times(1)).findOne(1L);
        verify(projectMapper, times(1)).fromProjectToDto(project);
        verifyNoMoreInteractions(projectDao);
        verifyNoMoreInteractions(projectMapper);
    }
}