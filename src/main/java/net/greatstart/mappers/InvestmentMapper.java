package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    InvestmentMapper MAPPER = Mappers.getMapper( InvestmentMapper.class );

    DtoInvestment fromInvestmentToDto(Investment investment, @Context CycleAvoidingMappingContext context);

    Investment investmentFromDto(DtoInvestment dtoInvestment, @Context CycleAvoidingMappingContext context);

}
