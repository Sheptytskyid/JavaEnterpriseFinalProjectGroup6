package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class ProjectServiceTest {

    @Mock
    private ProjectDao mockProjectDao;
    @Mock
    private UserService mockUserService;
    @InjectMocks
    private ProjectService projectService;
    private Project project = new Project();

    @Test(timeout = 2000)
    public void createProject() throws Exception {
        projectService.saveProject(project);
        verify(mockProjectDao, times(1)).save(project);
    }

    @Test(timeout = 2000)
    public void deleteProject() throws Exception {
        projectService.deleteProject(1L);
        verify(mockProjectDao, times(1)).delete(1L);
    }

    @Test(timeout = 2000)
    public void getProjectById() throws Exception {
        projectService.getProjectById(1L);
        verify(mockProjectDao, times(1)).findOne(1L);
    }

    @Test(timeout = 2000)
    public void getAllProjects() throws Exception {
        projectService.getAllProjects();
        verify(mockProjectDao, times(1)).findAll();
    }

    @Test(timeout = 2000)
    public void getNProjects() throws Exception {
        projectService.getNProjects(10);
        Pageable pageable = new PageRequest(0, 10);
        verify(mockProjectDao, times(1)).findAll(pageable);
    }

    @Test
    public void getAllProjectsOfUser() {
        String email = "admin@goit.ua";
        when(mockUserService.getUserByEmail(email)).thenReturn(new User());
        projectService.getAllProjectsOfUser(email);
        verify(mockUserService).getUserByEmail(email);
        verify(mockProjectDao).findOne(0L);
    }
}