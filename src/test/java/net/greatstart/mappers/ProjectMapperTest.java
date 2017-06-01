package net.greatstart.mappers;

import net.greatstart.dto.DtoProject;
import net.greatstart.dto.DtoProjectDescription;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static net.greatstart.MapperHelper.*;
import static org.junit.Assert.*;

public class ProjectMapperTest {

    private ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    @Test
    public void fromProjectToDto() throws Exception {
        Project project = getTestProject(TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        DtoProject dtoProject = projectMapper.fromProjectToDto(project, new CycleAvoidingMappingContext());
        assertNotNull(dtoProject);
        assertEquals(project.getId(), dtoProject.getId().longValue());
        DtoProjectDescription dtoDesc = dtoProject.getDesc();
        assertNotNull(dtoDesc);
        ProjectDescription desc = project.getDesc();
        assertEquals(desc.getName(), dtoDesc.getName());
        assertEquals(desc.getGoal(), dtoDesc.getGoal());
        assertEquals(desc.getCost(), dtoDesc.getCost());
        assertEquals(desc.getMinInvestment(), dtoDesc.getMinInvestment());
        assertEquals(desc.getDateAdded(), dtoDesc.getDateAdded());
        assertEquals(desc.getDateStart(), dtoDesc.getDateStart());
        assertArrayEquals(desc.getImage(), dtoDesc.getImage());
        assertEquals(desc.getDescription(), dtoDesc.getDescription());
        assertNotNull(dtoProject.getCategory());
        assertEquals(project.getCategory().getName(), dtoProject.getCategory().getName());
    }

    @Test
    public void projectFromDto() throws Exception {
        DtoProject dtoProject = getTestDtoProject(TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        Project project = projectMapper.projectFromDto(dtoProject);
        assertNotNull(project);
        assertEquals(dtoProject.getId().longValue(), project.getId());
        DtoProjectDescription dtoDesc = dtoProject.getDesc();
        ProjectDescription desc = project.getDesc();
        assertNotNull(desc);
        assertEquals(dtoDesc.getName(), desc.getName());
        assertEquals(dtoDesc.getGoal(), desc.getGoal());
        assertEquals(dtoDesc.getDescription(), desc.getDescription());
        assertEquals(dtoDesc.getCost(), desc.getCost());
        assertEquals(dtoDesc.getMinInvestment(), desc.getMinInvestment());
        assertEquals(dtoDesc.getDateAdded(), desc.getDateAdded());
        assertEquals(dtoDesc.getDateStart(), desc.getDateStart());
        assertArrayEquals(dtoDesc.getImage(), desc.getImage());

    }

}