package net.greatstart.controller;

import net.greatstart.model.Project;
import net.greatstart.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public boolean createProject(Project project) {
        return projectService.createProject(project);
    }

    public boolean updateProject(Project project) {
        return projectService.updateProject(project);
    }

    public boolean deleteProject(Project project) {
        return projectService.deleteProject(project);
    }

    public Project getProjectById(long id) {
        return projectService.getProjectById(id);
    }

    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
