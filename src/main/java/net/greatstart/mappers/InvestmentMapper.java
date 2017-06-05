package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapstruct mapper to convert between {@link net.greatstart.model.Investment} and
 * {@link net.greatstart.dto.DtoInvestment}.
 */

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    @Mappings({
            @Mapping(target = "project.owner.photo", ignore = true),
            @Mapping(target = "project.desc.image", ignore = true),
            @Mapping(target = "investor.photo", ignore = true)})
    DtoInvestment fromInvestmentToDto(Investment investment);

    Investment investmentFromDto(DtoInvestment dtoInvestment);

    @Mappings({
            @Mapping(target = "address", source = "contact.address"),
            @Mapping(target = "phoneNumber", source = "contact.phoneNumber"),
            @Mapping(target = "dtoInvestments", source = "investments")})
    DtoUserProfile fromUserToDtoProfile(User user);

    @Mappings({
            @Mapping(source = "investments", target = "dtoInvestments"),
            @Mapping(target = "owner.dtoInvestments", ignore = true),
            @Mapping(target = "owner.photo", ignore = true)})
    DtoProject fromProjectToDto(Project project);
}
