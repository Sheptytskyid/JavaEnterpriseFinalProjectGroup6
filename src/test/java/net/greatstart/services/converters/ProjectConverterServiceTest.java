package net.greatstart.services.converters;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Category;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.model.User;
import net.greatstart.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectConverterServiceTest {
    private static final String NAME = "Test name";
    private static final String NEW_NAME = "New name";
    private static final String GOAL = "Test goal";
    private static final String DESC = "Test description";
    private static final LocalDate DATE_START = LocalDate.now();
    private static final LocalDate DATE_ADDED = LocalDate.now();
    private static final Long ID = 1L;
    private static final byte[] IMG = {1, 0, 0, 1, 5};
    private static final BigDecimal COST = new BigDecimal(1000);
    private static final BigDecimal MIN_INVESTMENT = new BigDecimal(10);
    private static final String CATEGORY = "Test category";

    @Mock
    private CategoryService categoryService;

    @Mock
    private MockMultipartFile image;

    @InjectMocks
    private ProjectConverterService projectConverter;

    @Test
    public void fromValidProjectToDto() throws Exception {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        project.setOwner(new User());
        desc.setAddDate(DATE_ADDED);
        desc.setAddStart(DATE_START);
        desc.setName(NAME);
        desc.setGoal(GOAL);
        desc.setImage(IMG);
        desc.setDescription(DESC);
        desc.setCost(COST);
        desc.setMinInvestment(MIN_INVESTMENT);
        desc.setIsVerified(true);
        desc.setOther("");
        desc.setLogotype("");
        project.setDesc(desc);
        Category category = new Category();
        category.setName(CATEGORY);
        project.setCategory(category);
        project.setId(ID);
        DtoProject dtoProject = projectConverter.fromProjectToDto(project);
        assertNotNull(dtoProject);
        assertEquals(ID, dtoProject.getId());
        assertEquals(NAME, dtoProject.getName());
        assertEquals(GOAL, dtoProject.getGoal());
        assertEquals(DESC, dtoProject.getDescription());
        assertEquals(IMG, dtoProject.getImage());
        assertEquals(CATEGORY, dtoProject.getCategory());
        assertEquals(COST, dtoProject.getCost());
        assertEquals(MIN_INVESTMENT, dtoProject.getMinInvestment());
        assertEquals(DATE_ADDED, dtoProject.getDateAdded());
        assertEquals(DATE_START, dtoProject.getDateStart());
    }

    @Test
    public void fromNullProjectToDto() throws Exception {
        DtoProject dtoProject = projectConverter.fromProjectToDto(null);
        assertNotNull(dtoProject);
    }

    @Test
    public void updateProjectFromDto() throws Exception {
        Project project = new Project();
        project.setId(ID);
        ProjectDescription desc = new ProjectDescription();
        desc.setName(NAME);
        DtoProject dtoProject = new DtoProject();
        dtoProject.setId(ID);
        dtoProject.setName(NEW_NAME);
        projectConverter.updateProjectFromDto(project, dtoProject, image);
        assertEquals(NEW_NAME, project.getDesc().getName());
    }

    @Test
    public void newProjectFromDto() throws Exception {
        DtoProject dtoProject = new DtoProject();
        dtoProject.setName(NAME);
        dtoProject.setGoal(GOAL);
        dtoProject.setDescription(DESC);
        dtoProject.setMinInvestment(MIN_INVESTMENT);
        dtoProject.setCategory(CATEGORY);
        dtoProject.setCost(COST);
        dtoProject.setDateAdded(DATE_ADDED);
        Category category = new Category();
        category.setName(CATEGORY);
        when(categoryService.findCategoryByName(CATEGORY)).thenReturn(category);
        Project project = projectConverter.newProjectFromDto(dtoProject, image);
        assertNotNull(project);
        assertEquals(NAME, project.getDesc().getName());
        assertEquals(GOAL, project.getDesc().getGoal());
        assertEquals(DESC, project.getDesc().getDescription());
        assertEquals(MIN_INVESTMENT, project.getDesc().getMinInvestment());
        assertEquals(COST, project.getDesc().getCost());
        assertEquals(DATE_ADDED, project.getDesc().getAddDate());
        assertEquals(CATEGORY, project.getCategory().getName());
    }

}