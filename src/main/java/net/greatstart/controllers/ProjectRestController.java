package net.greatstart.controllers;

import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Project;
import net.greatstart.services.CategoryService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectRestController {
    private static final String REDIRECT_TO_PROJECTS = "redirect:/project/";
    private static final String PROJECTS = "project/projects";
    private static final String PROJECT = "dtoProject";

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private ProjectService projectService;
    private UserService userService;
    private CategoryService categoryService;

    private ProjectMapper projectMapper;

    @Autowired
    public ProjectRestController(ProjectService projectService,
                             UserService userService,
                             ProjectMapper projectMapper,
                             CategoryService categoryService) {
        this.projectService = projectService;
        this.userService = userService;
        this.projectMapper = projectMapper;
        this.categoryService = categoryService;
    }

    @RequestMapping({"", "/"})
    public ResponseEntity<Collection<Project>> getProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return  new ResponseEntity<Collection<Project>>(projectList, HttpStatus.OK);
    }

}
