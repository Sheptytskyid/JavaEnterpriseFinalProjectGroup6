package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper MAPPER = Mappers.getMapper( ProjectMapper.class );

    @Mapping(source = "investments", target = "dtoInvestments")
    DtoProject fromProjectToDto(Project project, @Context CycleAvoidingMappingContext context);

    Project projectFromDto(DtoProject dtoProject);

    List<DtoInvestment> investmentsToDtoInvestments(List<Investment> investments);

    @Mapping(target = "project.desc.image", ignore = true)
    DtoInvestment fromInvestmentToDto(Investment investment);
}
