package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    InvestmentMapper MAPPER = Mappers.getMapper( InvestmentMapper.class );
    @Mappings({
            @Mapping(target = "project.owner.photo", ignore = true),
            @Mapping(target = "project.desc.image", ignore = true),
            @Mapping(target = "investor.photo", ignore = true)
    })
    DtoInvestment fromInvestmentToDto(Investment investment, @Context CycleAvoidingMappingContext context);

    Investment investmentFromDto(DtoInvestment dtoInvestment);

}
