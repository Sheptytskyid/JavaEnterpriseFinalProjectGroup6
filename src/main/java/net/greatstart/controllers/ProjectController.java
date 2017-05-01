package net.greatstart.controllers;

import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private static final String REDIRECT_TO_PROJECTS = "redirect:/project/";
    public static final String PROJECTS = "project/projects";

    private ProjectService projectService;
    private UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView showProjects() {
        List<Project> projectList = projectService.getAllProjects();
        ModelAndView model = new ModelAndView(PROJECTS);
        model.addObject(projectList);
        model.addObject("listTitle", "All projects");
        return model;
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ModelAndView showMyProjects(Principal principal) {
        List<Project> projectList = projectService.getAllProjectsOfUser(principal.getName());
        ModelAndView model = new ModelAndView(PROJECTS);
        model.addObject(projectList);
        model.addObject("listTitle", "My projects");
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getAddProjectForm() {
        ModelAndView model = new ModelAndView("project/add_project");
        model.addObject("project", new Project());
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView addProject(Project project,
                             Errors errors,
                             Principal principal) {
        if (errors.hasErrors()) {
            return new ModelAndView("project/add_project");
        }
        User owner = userService.getUserByEmail(principal.getName());
        project.setOwner(owner);
        projectService.createProject(project);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView getUpdateProjectForm(@PathVariable("id") Long id) {
        if (id > 0) {
            ModelAndView model = new ModelAndView("project/update_project");
            Project project = projectService.getProjectById(id);
            model.addObject("project", project);
            return model;
        }
        return new ModelAndView(PROJECTS);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public ModelAndView updateProject(@PathVariable Long id, @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("project/update_project");
        }
        projectService.updateProject(project);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

}
