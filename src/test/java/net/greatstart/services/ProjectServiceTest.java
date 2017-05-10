package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @Mock
    private ProjectDao projectDao;
    @Mock
    private UserService userService;
    @Mock
    private Project project = new Project();
    @InjectMocks
    private ProjectService projectService;

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
    public void invokeProjectDaoWhenGetProjectById() throws Exception {
        projectService.getProjectById(1L);
        verify(projectDao, times(1)).findOne(1L);
    }

    @Test(timeout = 2000)
    public void invokeProjectDaoWhenGetAllProjects() throws Exception {
        when(projectDao.findAll()).thenReturn(new ArrayList<>());
        projectService.getAllProjects();
        verify(projectDao, times(1)).findAll();
    }

    @Test(timeout = 2000)
    public void invokeDaoWhenGetNProjects() throws Exception {
        projectService.getNProjects(10);
        Pageable pageable = new PageRequest(0, 10);
        verify(projectDao, times(1)).findAll(pageable);
    }

    @Test
    public void invokeDaoWhenGetAllProjectsOfUser() {
        String email = "test@test.ua";
        User user = new User();
        user.setEmail(email);
        when(userService.getUserByEmail(email)).thenReturn(user);
        projectService.getAllProjectsOfUser(email);
        verify(projectDao, times(1)).findByOwner(user);
    }
}