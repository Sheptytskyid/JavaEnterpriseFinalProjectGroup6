package net.greatstart.services;

import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Contact;
import net.greatstart.model.User;
import net.greatstart.services.converters.UserConverterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterServiceTest {

    private static final String EMAIL = "a@example.com";
    private static final String NAME = "UserName";
    private static final String LAST_NAME = "UserLastName";
    private static final String INITIALS = "U.U.";
    private static final String ADDRESS = "Address";
    private static final String NEW_ADDRESS = "New address";
    private static final String PHONE = "+38 044 123 45 68";
    private static final String NEW_PHONE = "+38 044 321 54 86";
    private static final long ID = 1L;
    private User user;
    private DtoUserProfile dtoUserProfile;
    @InjectMocks
    private UserConverterService userConverter;

    @Before
    public void setUp() {
        user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setLastName(LAST_NAME);
        Contact contact = new Contact(ADDRESS, PHONE);
        user.setContact(contact);
        dtoUserProfile = new DtoUserProfile();
        dtoUserProfile.setAddress(NEW_ADDRESS);
        dtoUserProfile.setPhoneNumber(NEW_PHONE);
    }

    @Test
    public void fromUserToDtoProfile() throws Exception {
        DtoUserProfile dtoUser = userConverter.fromUserToDtoProfile(user);
        assertEquals(ID, dtoUser.getId().longValue());
        assertEquals(EMAIL, dtoUser.getEmail());
        assertEquals(NAME, dtoUser.getName());
        assertEquals(ADDRESS, dtoUser.getAddress());
        assertEquals(PHONE, dtoUser.getPhoneNumber());
        assertEquals(INITIALS, dtoUser.getInitial());
    }

    @Test
    public void updateUserFromDto() throws Exception {
        userConverter.updateUserFromDto(user, dtoUserProfile);
        assertEquals(NEW_ADDRESS, user.getContact().getAddress());
        assertEquals(NEW_PHONE, user.getContact().getPhoneNumber());
    }
}
