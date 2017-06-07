package net.greatstart.mappers;

import net.greatstart.dto.DtoProject;
import net.greatstart.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapstruct provided mapper to convert between {@link net.greatstart.model.Project} and
 * {@link net.greatstart.dto.DtoProject}.
 */

/**
 * Mapstruct provided mapper to convert between {@link net.greatstart.model.Project} and
 * {@link net.greatstart.dto.DtoProject}.
 */

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "owner.photo", ignore = true)
    DtoProject fromProjectToDto(Project project);

    Project projectFromDto(DtoProject dtoProject);

}
