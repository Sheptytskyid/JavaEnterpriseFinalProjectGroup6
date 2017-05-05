package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public void saveProject(Project project) {
        projectDao.save(project);
    }

    public void deleteProject(long id) {
        projectDao.delete(id);
    }

    public Project getProjectById(long id) {
        return projectDao.findOne(id);
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectDao.findAll().forEach(projects::add);
        return projects;
    }

    public List<Project> getNProjects(int numberOfProjects) {
        Pageable pageable = new PageRequest(0, numberOfProjects);
        return projectDao.findAll(pageable);
    }

    public List<Project> getAllProjectsOfUser(String email) {
        User user = userService.getUserByEmail(email);
        return projectDao.findByUserId(user.getId());
    }
}
