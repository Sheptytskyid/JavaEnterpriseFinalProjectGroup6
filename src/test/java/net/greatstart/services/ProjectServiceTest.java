package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class ProjectServiceTest {

    @Mock
    private ProjectDao projectDao;
    @Mock
    private UserService userService;
    @Mock
    private Project project = new Project();
    @InjectMocks
    private ProjectService projectService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        projectService = new ProjectService(projectDao, userService);
    }

    @Test(timeout = 2000)
    public void shouldInvokeProjectDaoWhenSaveProject() throws Exception {
        projectService.saveProject(project);
        verify(projectDao, times(1)).save(project);
    }

    @Test(timeout = 2000)
    public void shouldInvokeProjectDaoWhenDeleteProject() throws Exception {
        projectService.deleteProject(1L);
        verify(projectDao, times(1)).delete(1L);
    }

    @Test(timeout = 2000)
    public void shouldInvokeProjectDaoWhenGetProjectById() throws Exception {
        projectService.getProjectById(1L);
        verify(projectDao, times(1)).findOne(1L);
    }

    @Test(timeout = 2000)
    public void shouldInvokeProjectDaoWhenGetAllProjects() throws Exception {
        when(projectDao.findAll()).thenReturn(new ArrayList<>());
        projectService.getAllProjects();
        verify(projectDao, times(1)).findAll();
    }

    @Test(timeout = 2000)
    public void getNProjects() throws Exception {
        projectService.getNProjects(10);
        Pageable pageable = new PageRequest(0, 10);
        verify(projectDao, times(1)).findAll(pageable);
    }

    @Test
    public void getAllProjectsOfUser() {
        String email = "test@test.ua";
        User user = new User();
        user.setEmail(email);
        when(userService.getUserByEmail(email)).thenReturn(user);
        projectService.getAllProjectsOfUser(email);
        verify(projectDao, times(1)).findByOwner(user);
    }
}