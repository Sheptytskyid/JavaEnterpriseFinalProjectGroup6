package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Category;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.CategoryService;
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
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
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

    @GetMapping({"/api/project", "/api/project/"})
    public ResponseEntity<Collection<Project>> getProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/projects/my")
    public ResponseEntity<Collection<Project>> getMyProjects(Principal principal) {
        List<Project> projectList = projectService.getAllProjectsOfUser(principal.getName());
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/api/project/{id}")
    public ResponseEntity<DtoProject> getProjectById(@PathVariable("id") long id) {
        DtoProject project = projectMapper.fromProjectToDto(projectService.getProjectById(id));
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
            @Valid @RequestBody DtoProject dtoProject, Principal principal) {
        User currentUser = userService.getUserByEmail(principal.getName());
        Project currentProject = projectMapper.projectFromDto(dtoProject);
        if (currentUser.getId() != currentProject.getOwner().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        currentProject = projectService.saveProject(currentProject);
        if (currentProject != null) {
            return new ResponseEntity<>(projectMapper.fromProjectToDto(currentProject), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @PostMapping({"/api/project", "/api/project/"})
    public ResponseEntity<DtoProject> newProject(
            @Valid @RequestBody DtoProject dtoProject, Principal principal) {
        User owner = userService.getUserByEmail(principal.getName());
        Category category = categoryService.findCategoryByName(dtoProject.getCategory().getName());
        Project currentProject = projectMapper.projectFromDto(dtoProject);
        currentProject.setCategory(category);
        currentProject.setOwner(owner);
        currentProject.getDesc().setDateAdded(LocalDate.now());
        currentProject = projectService.saveProject(currentProject);
        if (currentProject != null) {
            return new ResponseEntity<>(projectMapper.fromProjectToDto(currentProject), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/api/project/{id}")
    public ResponseEntity<DtoProject> deleteProject(@PathVariable("id") long id, Principal principal) {
        Project project = projectService.getProjectById(id);
        if (!principal.getName().equals(project.getOwner().getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
