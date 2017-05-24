package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    DtoInvestment fromInvestmentToDto(Investment investment);

    Investment investmentFromDto(DtoInvestment dtoInvestment);

}
