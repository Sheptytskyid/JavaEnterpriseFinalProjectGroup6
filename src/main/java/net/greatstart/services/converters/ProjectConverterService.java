package net.greatstart.services.converters;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Category;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class ProjectConverterService {
    private CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(ProjectConverterService.class);

    @Autowired
    public ProjectConverterService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public DtoProject fromProjectToDto(Project project) {
        DtoProject dtoProject = new DtoProject();
        if (project != null) {
            ProjectDescription desc = project.getDesc();
            dtoProject.setId(project.getId());
            dtoProject.setName(desc.getName());
            dtoProject.setGoal(desc.getGoal());
            dtoProject.setDescription(desc.getDescription());
            Category category = project.getCategory();
            if (category != null) {
                dtoProject.setCategory(category.getName());
            }
            dtoProject.setDateStart(desc.getAddStart());
            dtoProject.setDateAdded(desc.getAddDate());
            dtoProject.setCost(desc.getCost());
            dtoProject.setMinInvestment(desc.getMinInvestment());
            dtoProject.setImage(desc.getImage());
        }
        return dtoProject;
    }

    public void updateProjectFromDto(Project project, DtoProject dtoProject, MultipartFile image) {
        if (dtoProject != null) {
            saveImage(dtoProject, image);
            ProjectDescription desc = getProjectDescFromDto(dtoProject);
            project.setDesc(desc);
            Category category = categoryService.findCategoryByName(dtoProject.getCategory());
            project.setCategory(category);
        }
    }

    public Project newProjectFromDto(DtoProject dtoProject, MultipartFile image) {
        Project project = new Project();
        if (dtoProject != null) {
            saveImage(dtoProject, image);
            ProjectDescription desc = getProjectDescFromDto(dtoProject);
            desc.setAddDate(LocalDate.now());
            Category category = categoryService.findCategoryByName(dtoProject.getCategory());
            project.setCategory(category);
            project.setDesc(desc);
        }
        return project;
    }

    private ProjectDescription getProjectDescFromDto(DtoProject dtoProject) {
        ProjectDescription desc = new ProjectDescription();
        if (dtoProject != null) {
            desc.setName(dtoProject.getName());
            desc.setGoal(dtoProject.getGoal());
            desc.setDescription(dtoProject.getDescription());
            desc.setCost(dtoProject.getCost());
            desc.setMinInvestment(dtoProject.getMinInvestment());
            desc.setAddStart(dtoProject.getDateStart());
            desc.setAddDate(dtoProject.getDateAdded());
            desc.setImage(dtoProject.getImage());
        }
        return desc;
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
            dtoProject.setImage(content);
        }
    }

}
