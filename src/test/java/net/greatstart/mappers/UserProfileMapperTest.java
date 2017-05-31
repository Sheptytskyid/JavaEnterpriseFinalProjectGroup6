package net.greatstart.mappers;

import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Contact;
import net.greatstart.model.User;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static net.greatstart.MapperHelper.CONTEXT;
import static net.greatstart.MapperHelper.getTestDtoUserProfile;
import static net.greatstart.MapperHelper.getTestUser;
import static org.junit.Assert.*;

public class UserProfileMapperTest {
    private UserProfileMapper userMapper = Mappers.getMapper(UserProfileMapper.class);

    @Test
    public void fromUserToDtoProfile() throws Exception {
        User user = getTestUser();
        DtoUserProfile dtoUser = userMapper.fromUserToDtoProfile(user, CONTEXT);
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

}