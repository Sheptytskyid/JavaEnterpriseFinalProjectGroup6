package ua.goit.group6.services;

import ua.goit.group6.dao.ProjectOwnerDao;
import ua.goit.group6.model.ProjectOwner;
import java.util.List;

public class ProjectOwnerService {

    private ProjectOwnerDao projectOwnerDao;

    public ProjectOwnerService(ProjectOwnerDao projectOwnerDao) {
        this.projectOwnerDao = projectOwnerDao;
    }


    public void createProjectOwner(ProjectOwner projectOwner) {
        projectOwnerDao.create(projectOwner);
    }

    public ProjectOwner getProjectOwner(String email) {
        return projectOwnerDao.getByEmail(email);
    }

    public boolean updateProjectOwner(ProjectOwner projectOwner) {
        return projectOwnerDao.update(projectOwner);
    }

    public List<ProjectOwner> getAllProjectOwners() {
        return projectOwnerDao.getAll();
    }

}
