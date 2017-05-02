package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    private ProjectDao projectDao;
    private UserService userService;

    @Autowired
    public ProjectService(ProjectDao projectDao, UserService userService) {
        this.projectDao = projectDao;
        this.userService = userService;
    }

    public boolean createProject(Project project) {
        return projectDao.create(project);
    }

    public boolean updateProject(Project project) {
        return projectDao.update(project);
    }

    public boolean deleteProject(long id) {
        Project project = getProjectById(id);
        return projectDao.delete(project);
    }

    public Project getProjectById(long id) {
        return projectDao.getById(id);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAll();
    }

    public List<Project> getNProjects(int numberOfProjects) {
        return projectDao.getNRecords(numberOfProjects);
    }

    public List<Project> getAllProjectsOfUser(String email) {
        User user = userService.getUserByEmail(email);
        return projectDao.getByUserId(user.getId());
    }
}
