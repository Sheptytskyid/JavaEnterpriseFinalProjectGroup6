package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper MAPPER = Mappers.getMapper(ProjectMapper.class);

    @Mappings({
            @Mapping(source = "investments", target = "dtoInvestments"),
            @Mapping(target = "owner.dtoInvestments", ignore = true),
            @Mapping(target = "owner.photo", ignore = true)
    })
    DtoProject fromProjectToDto(Project project, @Context CycleAvoidingMappingContext context);

    Project projectFromDto(DtoProject dtoProject);

    List<DtoInvestment> investmentsToDtoInvestments(List<Investment> investments);

    @Mappings({
            @Mapping(target = "project.desc.image", ignore = true),
            @Mapping(target = "project.owner.photo", ignore = true),
            @Mapping(target = "investor.photo", ignore = true)
    })
    DtoInvestment fromInvestmentToDto(Investment investment);

}
