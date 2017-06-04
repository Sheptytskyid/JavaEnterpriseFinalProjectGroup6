package net.greatstart.services;

import net.greatstart.dao.ProjectDao;
import net.greatstart.dto.DtoProject;
import net.greatstart.mappers.ProjectMapper;
import net.greatstart.model.Category;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    private ProjectDao projectDao;
    private UserService userService;
    private ProjectMapper projectMapper;
    private CategoryService categoryService;

    @Autowired
    public ProjectService(ProjectDao projectDao,
                          UserService userService,
                          ProjectMapper projectMapper,
                          CategoryService categoryService) {
        this.projectDao = projectDao;
        this.userService = userService;
        this.projectMapper = projectMapper;
        this.categoryService = categoryService;
    }

    public Project saveProject(Project project) {
        return projectDao.save(project);
    }

    public DtoProject saveDtoProject(DtoProject dtoProject) {
        Project project = projectMapper.projectFromDto(dtoProject);
        project = saveProject(project);
        return projectMapper.fromProjectToDto(project);
    }

    public DtoProject createNewProjectFromDto(DtoProject dtoProject) {
        User owner = userService.getLoggedInUser();
        Category category = categoryService.findCategoryByName(dtoProject.getCategory().getName());
        Project project = projectMapper.projectFromDto(dtoProject);
        project.setOwner(owner);
        project.setCategory(category);
        project.getDesc().setDateAdded(LocalDate.now());
        project = saveProject(project);
        return projectMapper.fromProjectToDto(project);
    }

    public void deleteProject(long id) {
        projectDao.delete(id);
    }

    public Project getProjectById(long id) {
        return projectDao.findOne(id);
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectDao.findAll().forEach(projects::add);
        return projects;
    }

    public List<Project> getNProjects(int numberOfProjects) {
        Pageable pageable = new PageRequest(0, numberOfProjects);
        return projectDao.findAll(pageable).getContent();
    }

    public List<Project> getAllProjectsOfCurrentUser() {
        User user = userService.getLoggedInUser();
        return projectDao.findByOwner(user);
    }

    public DtoProject getDtoProjectById(Long id) {
        return projectMapper.fromProjectToDto(projectDao.findOne(id));
    }
}
