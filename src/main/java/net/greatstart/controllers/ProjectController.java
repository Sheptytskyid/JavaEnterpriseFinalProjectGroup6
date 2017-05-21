package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.services.converters.ProjectConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private static final String REDIRECT_TO_PROJECTS = "redirect:/project/";
    private static final String PROJECTS = "project/projects";
    private static final String PROJECT = "dtoProject";

    private ProjectService projectService;
    private UserService userService;
    private ProjectConverterService projectConverter;

    @Autowired
    public ProjectController(ProjectService projectService,
                             UserService userService,
                             ProjectConverterService projectConverter) {
        this.projectService = projectService;
        this.userService = userService;
        this.projectConverter = projectConverter;
    }

    @RequestMapping({"", "/"})
    public ModelAndView showProjects() {
        List<Project> projectList = projectService.getAllProjects();
        ModelAndView model = new ModelAndView(PROJECTS);
        model.addObject(projectList);
        model.addObject("listTitle", "All projects");
        return model;
    }

    @GetMapping(value = "/my")
    public ModelAndView showMyProjects(Principal principal) {
        List<Project> projectList = projectService.getAllProjectsOfUser(principal.getName());
        ModelAndView model = new ModelAndView(PROJECTS);
        model.addObject(projectList);
        model.addObject("listTitle", "My projects");
        return model;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView showProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        ModelAndView model = new ModelAndView("project/project_page");
        model.addObject(PROJECT, project);
        model.addObject("investedAmount",
                projectService.getProjectById(id)
                        .getInvestments().stream()
                        .map(Investment::getSum)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
        return model;
    }

    @GetMapping(value = "/new")
    public ModelAndView getAddProjectForm() {
        ModelAndView model = new ModelAndView("project/add_project");
        model.addObject(PROJECT, new DtoProject());
        return model;
    }

    @PostMapping(value = "/new")
    public ModelAndView addProject(@Valid DtoProject dtoProject,
                                   Errors errors,
                                   Principal principal,
                                   @RequestParam(value = "file", required = false) MultipartFile file) {
        if (errors.hasErrors()) {
            return new ModelAndView("project/add_project");
        }
        User owner = userService.getUserByEmail(principal.getName());
        Project project = projectConverter.newProjectFromDto(dtoProject, file);
        project.setOwner(owner);
        projectService.saveProject(project);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @GetMapping(value = "/{id}/update")
    public ModelAndView getUpdateProjectForm(@PathVariable("id") Long id) {
        if (id > 0) {
            ModelAndView model = new ModelAndView("project/update_project");
            Project project = projectService.getProjectById(id);
            model.addObject(PROJECT, projectConverter.fromProjectToDto(project));
            return model;
        }
        return new ModelAndView(PROJECTS);
    }

    @PostMapping(value = "/{id}/update")
    public ModelAndView updateProject(@PathVariable Long id, @Valid DtoProject dtoProject, Errors errors,
                                      @RequestParam(value = "file", required = false) MultipartFile file) {
        if (errors.hasErrors()) {
            return new ModelAndView("project/update_project");
        }
        Project project = projectService.getProjectById(id);
        projectConverter.updateProjectFromDto(project, dtoProject, file);
        projectService.saveProject(project);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @GetMapping(value = "/{id}/delete")
    public ModelAndView deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @GetMapping(value = "/{id}/image")
    @ResponseBody
    public byte[] downloadImage(@PathVariable("id") Long id) {
        DtoProject project = projectConverter.fromProjectToDto(projectService.getProjectById(id));
        return project.getImage();
    }

}
