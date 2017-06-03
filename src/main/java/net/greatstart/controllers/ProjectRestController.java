package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Project;
import net.greatstart.services.CategoryService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectRestController {

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

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<Project>> getProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return  new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DtoProject> getProjectById(@PathVariable("id") long id) {
        DtoProject project = projectMapper.fromProjectToDto(projectService.getProjectById(id));
        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<DtoProject> updateProject(
            @PathVariable("id") long id,
            @Valid @RequestBody DtoProject dtoProject) {
        Project currentProject = projectService.saveProject(projectMapper.projectFromDto(dtoProject));
        if (currentProject != null) {
            return new ResponseEntity<>(projectMapper.fromProjectToDto(currentProject), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
