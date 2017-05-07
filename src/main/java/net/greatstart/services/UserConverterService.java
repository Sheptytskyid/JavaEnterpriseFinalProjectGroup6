package net.greatstart.services;

import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Contact;
import net.greatstart.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverterService {

    public DtoUserProfile fromUserToDtoProfile(User user) {
        StringBuilder initials = new StringBuilder();
        String lastName = user.getLastName();
        if (lastName != null && !lastName.equals("")) {
            initials.append(user.getName().substring(0, 1).toUpperCase())
                    .append(".")
                    .append(lastName.substring(0, 1).toUpperCase())
                    .append(".");
        } else {
            initials.append(user.getName().substring(0, 1).toUpperCase());
        }

        DtoUserProfile dtoUser = new DtoUserProfile();
        dtoUser.setId(user.getId());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setName(user.getName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setInitial(initials.toString());
        if (user.getPhoto() != null) {
            dtoUser.setPhoto(user.getPhoto());
        }
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
        if (dtoUser.getPhoto() != null) {
            user.setPhoto(dtoUser.getPhoto());
        }
    }
}
