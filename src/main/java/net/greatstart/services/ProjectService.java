package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public boolean createProject(Project project) {
        return projectDao.create(project);
    }

    public boolean updateProject(Project project) {
        return projectDao.update(project);
    }

    public boolean deleteProject(Project project) {
        return projectDao.delete(project);
    }

    public Project getProjectById(long id) {
        return projectDao.getById(id);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAll();
    }
}
