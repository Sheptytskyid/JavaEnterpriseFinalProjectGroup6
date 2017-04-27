package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class ProjectServiceTest {
    @Mock
    ProjectDao mockProjectDao;
    ProjectService projectService;
    Project project = new Project();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        projectService = new ProjectService(mockProjectDao);
    }

    @Test
    public void createProject() throws Exception {
        projectService.createProject(project);
        verify(mockProjectDao).create(project);
    }

    @Test
    public void updateProject() throws Exception {
        projectService.updateProject(project);
        verify(mockProjectDao).update(project);
    }

    @Test
    public void deleteProject() throws Exception {
        projectService.deleteProject(1L);
        verify(mockProjectDao).getById(1L);
        verify(mockProjectDao).delete(null);
    }

    @Test
    public void getProjectById() throws Exception {
        projectService.deleteProject(1L);
        verify(mockProjectDao).getById(1L);
    }

    @Test
    public void getAllProjects() throws Exception {
        projectService.getAllProjects();
        verify(mockProjectDao).getAll();
    }

    @Test
    public void getNProjects() throws Exception {
        projectService.getNProjects(10);
        verify(mockProjectDao).getNRecords(10);
    }

}