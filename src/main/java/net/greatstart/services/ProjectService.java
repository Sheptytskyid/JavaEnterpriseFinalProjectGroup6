package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    private ProjectDao projectDao;

    @Autowired
    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

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
