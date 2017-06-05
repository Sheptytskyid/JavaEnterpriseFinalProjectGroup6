package net.greatstart.mappers;

import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Contact;
import net.greatstart.model.User;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static net.greatstart.MapperHelper.getFullTestUser;
import static net.greatstart.MapperHelper.getTestDtoUserProfile;
import static org.junit.Assert.*;

public class UserProfileMapperTest {
    private UserProfileMapper userMapper = Mappers.getMapper(UserProfileMapper.class);

    @Test
    public void fromUserToDtoProfile() throws Exception {
        User user = getFullTestUser();
        DtoUserProfile dtoUser = userMapper.fromUserToDtoProfile(user);
        assertNotNull(dtoUser);
        Contact contact = user.getContact();
        assertEquals(user.getId(), dtoUser.getId().longValue());
        assertEquals(user.getName(), dtoUser.getName());
        assertEquals(user.getLastName(), dtoUser.getLastName());
        assertEquals(user.getEmail(), dtoUser.getEmail());
        assertEquals(contact.getAddress(), dtoUser.getAddress());
        assertEquals(contact.getPhoneNumber(), dtoUser.getPhoneNumber());
        assertArrayEquals(user.getPhoto(), dtoUser.getPhoto());
    }

    @Test
    public void fromDtoProfileToUser() throws Exception {
        DtoUserProfile dtoUser = getTestDtoUserProfile();
        User user = userMapper.fromDtoProfileToUser(dtoUser);
        assertNotNull(user);
        Contact contact = user.getContact();
        assertNotNull(contact);
        assertEquals(dtoUser.getId().longValue(), user.getId());
        assertEquals(dtoUser.getName(), user.getName());
        assertEquals(dtoUser.getLastName(), user.getLastName());
        assertEquals(dtoUser.getEmail(), user.getEmail());
        assertEquals(dtoUser.getAddress(), contact.getAddress());
        assertEquals(dtoUser.getPhoneNumber(), contact.getPhoneNumber());
        assertArrayEquals(dtoUser.getPhoto(), user.getPhoto());
    }

    @Test
    public void fromUserToDtoUserProfileFull() throws Exception {
        //init
        User user = getFullTestUser();
        DtoUserProfile expected = getTestDtoUserProfile();
        /*for (DtoInvestment dtoInvestment : expected.getDtoInvestments()) {
            dtoInvestment.getProject().getOwner().setId(0L);
            dtoInvestment.getProject().getOwner().setAddress(null);
            dtoInvestment.getProject().getOwner().setDtoInvestments(null);
            dtoInvestment.getProject().getOwner().setEmail(null);
            dtoInvestment.getProject().getOwner().setLastName(null);
            dtoInvestment.getProject().getOwner().setName(null);
            dtoInvestment.getProject().getOwner().setPhoneNumber(null);
            dtoInvestment.getProject().getOwner().setPhoto(null);
            dtoInvestment.getProject().getDesc().setOther("");
            dtoInvestment.getInvestor().setAddress(null);
            dtoInvestment.getInvestor().setPhoneNumber(null);
            dtoInvestment.getInvestor().setPhoto(null);
        }*/
        //use
        DtoUserProfile result = userMapper.fromUserToDtoProfile(user);
        //check
        assertEquals(expected, result);
    }

}