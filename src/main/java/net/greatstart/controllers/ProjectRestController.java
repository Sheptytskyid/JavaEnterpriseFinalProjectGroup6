package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Project;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
public class ProjectRestController {

    private ProjectService projectService;
    private UserService userService;

    @Autowired
    public ProjectRestController(ProjectService projectService,
                                 UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping({"/api/project", "/api/project/"})
    public ResponseEntity<Collection<Project>> getProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/projects/my")
    public ResponseEntity<Collection<Project>> getMyProjects() {
        List<Project> projectList = projectService.getAllProjectsOfCurrentUser();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/api/project/{id}")
    public ResponseEntity<DtoProject> getProjectById(@PathVariable("id") long id) {
        DtoProject project = projectService.getDtoProjectById(id);
        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/api/project/{id}")
    public ResponseEntity<DtoProject> updateProject(
            @PathVariable("id") long id,
            @Valid @RequestBody DtoProject dtoProject) {
        if (userService.getLoggedInUser().getId() != dtoProject.getOwner().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        DtoProject currentProject = projectService.saveDtoProject(dtoProject);
        if (currentProject != null) {
            return new ResponseEntity<>(dtoProject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @PostMapping({"/api/project", "/api/project/"})
    public ResponseEntity<DtoProject> newProject(
            @Valid @RequestBody DtoProject dtoProject) {
        dtoProject = projectService.createNewProjectFromDto(dtoProject);
        if (dtoProject != null) {
            return new ResponseEntity<>(dtoProject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/api/project/{id}")
    public ResponseEntity<DtoProject> deleteProject(@PathVariable("id") long id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            if (userService.getLoggedInUser().getId() != project.getOwner().getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
