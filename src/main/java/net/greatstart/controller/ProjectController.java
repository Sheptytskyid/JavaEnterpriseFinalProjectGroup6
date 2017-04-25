package net.greatstart.controller;

import net.greatstart.model.Project;
import net.greatstart.services.ProjectService;

import java.util.List;

public class ProjectController {

    private ProjectService projectService;

    public boolean createProject(Project project) {
        return projectService.createProject(project);
    }

    public boolean updateProject(long id) {
        return projectService.updateProject(id);
    }

    public boolean deleteProject(long id) {
        return projectService.deleteProject(id);
    }

    public Project getProjectById(long id) {
        return projectService.getProjectById(id);
    }

    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
