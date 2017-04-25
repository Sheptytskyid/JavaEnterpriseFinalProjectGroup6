package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;

import java.util.List;

public class ProjectService {

    private ProjectDao projectDao;

    public boolean createProject(Project project) {
        return projectDao.create(project);
    }

    public boolean updateProject(long id) {
        Project project = getProjectById(id);
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
}
