package net.greatstart.mappers;

import net.greatstart.dto.DtoInterest;
import net.greatstart.model.InvestmentInterest;
import org.mapstruct.Mapper;

/**
 * Mapstruct provided mapper to convert between {@link net.greatstart.model.InvestmentInterest} and
 * {@link net.greatstart.dto.DtoInterest}.
 */

@Mapper(componentModel = "spring")
public interface InterestMapper {

    DtoInterest fromInterestToDto(InvestmentInterest investmentInterest);

    InvestmentInterest interestFromDto(DtoInterest dtoInterest);
}
