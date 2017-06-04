/**
 * This controller was used with JSP pages. After we switched to AngularJS, this controller is
 * no longer in use. It is preserved for its historical value.
 */

package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.CategoryService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private static final String REDIRECT_TO_PROJECTS = "redirect:/project/";
    private static final String PROJECTS = "project/projects";
    private static final String PROJECT = "dtoProject";

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private ProjectService projectService;
    private UserService userService;
    private CategoryService categoryService;

    private ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ProjectService projectService,
                             UserService userService,
                             ProjectMapper projectMapper,
                             CategoryService categoryService) {
        this.projectService = projectService;
        this.userService = userService;
        this.projectMapper = projectMapper;
        this.categoryService = categoryService;
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
        List<Project> projectList = projectService.getAllProjectsOfCurrentUser();
        ModelAndView model = new ModelAndView(PROJECTS);
        model.addObject(projectList);
        model.addObject("listTitle", "My projects");
        return model;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<DtoProject> showProject(@PathVariable Long id) {
        if (projectService.getProjectById(id) != null) {
            DtoProject dtoProject = projectService.getDtoProjectById(id);
            return new ResponseEntity<>(dtoProject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        saveImage(dtoProject, file);
        User owner = userService.getUserByEmail(principal.getName());
        Project project = projectMapper.projectFromDto(dtoProject);
        project.setOwner(owner);
        projectService.saveProject(project);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @GetMapping(value = "/{id}/update")
    public ModelAndView getUpdateProjectForm(@PathVariable("id") Long id) {
        if (id > 0) {
            ModelAndView model = new ModelAndView("project/update_project");
            Project project = projectService.getProjectById(id);
            model.addObject(PROJECT, projectMapper.fromProjectToDto(project));
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
        saveImage(dtoProject, file);
        Project project = projectMapper.projectFromDto(dtoProject);
        projectService.saveProject(project);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    @GetMapping(value = "/{id}/delete")
    public ModelAndView deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return new ModelAndView(REDIRECT_TO_PROJECTS);
    }

    private void saveImage(DtoProject dtoProject, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            byte[] content = null;
            try {
                logger.info("File name: " + image.getName());
                logger.info("File size: " + image.getSize());
                logger.info("File content type: " + image.getContentType());
                content = image.getBytes();
            } catch (IOException e) {
                logger.error("Error saving upload file", e);
            }
            dtoProject.getDesc().setImage(content);
        }
    }

    @GetMapping(value = "/{id}/image")
    @ResponseBody
    public byte[] downloadImage(@PathVariable("id") Long id) {
        DtoProject project = projectMapper
                .fromProjectToDto(projectService.getProjectById(id));
        return project.getDesc().getImage();
    }

}
