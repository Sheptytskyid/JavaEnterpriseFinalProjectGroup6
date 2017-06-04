package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Investment;
import net.greatstart.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mappings({
            @Mapping(target = "address", source = "contact.address"),
            @Mapping(target = "phoneNumber", source = "contact.phoneNumber"),
            @Mapping(target = "dtoInvestments", source = "investments")})
    DtoUserProfile fromUserToDtoProfile(User user);

    @Mappings({
            @Mapping(source = "address", target = "contact.address"),
            @Mapping(source = "phoneNumber", target = "contact.phoneNumber")})
    User fromDtoProfileToUser(DtoUserProfile dtoUserProfile);

    List<DtoInvestment> investmentsToDtoInvestments(List<Investment> investments);

    @Mappings({
            @Mapping(target = "project.desc.image", ignore = true),
            @Mapping(target = "project.owner.photo", ignore = true),
            @Mapping(target = "investor.photo", ignore = true)})
    DtoInvestment fromInvestmentToDto(Investment investment);
}
