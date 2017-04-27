package net.greatstart.controllers;

import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.model.User;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping("/projects")
    public String showProjects(Model model) {
        List<Project> projectList = projectService.getAllProjects();
        model.addAttribute(projectList);
        return "projects";
    }

    @RequestMapping(value = "/add_project", method = RequestMethod.GET)
    public String getAddProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "add_project";
    }

    @RequestMapping(value = "/add_project", method = RequestMethod.POST)
    public String addProject(Project project,
                             Errors errors,
                             Principal principal) {
        if (errors.hasErrors()) {
            return "add_project";
        }
        // TODO: get authenticated user once user authorization is ready
        User owner = userService.getByUsername("");
        project.setOwner(owner);
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/update_project", method = RequestMethod.GET)
    public String getUpdateProjectForm(@RequestParam("id") Long id, Model model) {
        if (id > 0) {
            Project project = projectService.getProjectById(id);
            model.addAttribute("project", project);
            return "update_project";
        }
        return "projects";
    }

    @RequestMapping(value = "/update_project", method = RequestMethod.POST)
    public String updateProject(@Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return "update_project";
        }
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/delete_project", method = RequestMethod.GET)
    public String deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }

}
