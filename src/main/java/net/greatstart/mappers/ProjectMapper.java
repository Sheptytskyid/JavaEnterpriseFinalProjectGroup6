package net.greatstart.mappers;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    DtoProject fromProjectToDto(Project project);

    Project projectFromDto(DtoProject dtoProject);
}
