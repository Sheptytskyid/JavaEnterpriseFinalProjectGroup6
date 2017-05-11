package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import net.greatstart.model.User;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @Mock
    private ProjectDao projectDao;
    @Mock
    private UserService userService;
    @InjectMocks
    private ProjectService projectService;
    private Project project = new Project();

    @Test(timeout = 2000)
    public void invokeProjectDaoWhenSaveProject() throws Exception {
        projectService.saveProject(project);
        verify(projectDao, times(1)).save(project);
    }

    @Test(timeout = 2000)
    public void invokeProjectDaoWhenDeleteProject() throws Exception {
        projectService.deleteProject(1L);
        verify(projectDao, times(1)).delete(1L);
    }

    @Test(timeout = 2000)
    public void returnProjectWhenGetProjectById() throws Exception {
        when(projectDao.findOne(1L)).thenReturn(project);
        assertEquals(projectService.getProjectById(1L),project);
    }

    @Test(timeout = 2000)
    public void invokeProjectDaoWhenGetAllProjects() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(projectDao.findAll()).thenReturn(projects);
        assertEquals(projectService.getAllProjects(),projects);
    }

    @Test(timeout = 2000)
    public void invokeDaoWhenGetNProjects() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        List<Project> projects = new ArrayList<>();
        Page<Project> page = new PageImpl<Project>(projects);
        when(projectDao.findAll(pageable)).thenReturn(page);
        assertEquals(projectService.getNProjects(10), projects);
    }

    @Test
    public void invokeDaoWhenGetAllProjectsOfUser() {
        String email = "test@test.ua";
        User user = new User();
        when(userService.getUserByEmail(email)).thenReturn(user);
        projectService.getAllProjectsOfUser(email);
        verify(projectDao, times(1)).findByOwner(user);
    }
}