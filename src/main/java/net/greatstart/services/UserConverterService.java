package net.greatstart.services;

import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Contact;
import net.greatstart.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverterService {

    public DtoUserProfile fromUserToDtoProfile(User user) {
        DtoUserProfile dtoUser = new DtoUserProfile();
        dtoUser.setId(user.getId());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setName(user.getName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setPhoto(user.getPhoto());
        Contact contact = user.getContact();
        if (contact != null) {
            dtoUser.setAddress(contact.getAddress());
            dtoUser.setPhoneNumber(contact.getPhoneNumber());
        }
        return dtoUser;
    }

    public void updateUserFromDto(User user, DtoUserProfile dtoUser) {
        Contact contact = new Contact(dtoUser.getAddress(), dtoUser.getPhoneNumber());
        user.setContact(contact);
        user.setName(dtoUser.getName());
        user.setLastName(dtoUser.getLastName());
        user.setPhoto(dtoUser.getPhoto());
    }
}
