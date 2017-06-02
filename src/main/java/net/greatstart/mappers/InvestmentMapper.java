package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    @Mappings({
            @Mapping(target = "project.owner.photo", ignore = true),
            @Mapping(target = "project.desc.image", ignore = true),
            @Mapping(target = "investor.photo", ignore = true)
    })
    DtoInvestment fromInvestmentToDto(Investment investment);

    Investment investmentFromDto(DtoInvestment dtoInvestment);

}
