package net.greatstart.services;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.Contact;
import net.greatstart.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverterService {

    public User fromDtoToUser(DtoUser dtoUser) {
        User user = new User();
        user.setName(dtoUser.getName());
        Contact contact = new Contact(dtoUser.getAddress(), dtoUser.getPhoneNumber());
        user.setContact(contact);
        return user;
    }

    public DtoUser fromUserToDto(User user) {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(user.getId());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setName(user.getName());
        Contact contact = user.getContact();
        if (contact != null) {
            dtoUser.setAddress(contact.getAddress());
            dtoUser.setPhoneNumber(contact.getPhoneNumber());
        }
        return dtoUser;
    }

    public void updateUserFromDto(User user, DtoUser dtoUser) {
        Contact contact = new Contact(dtoUser.getAddress(), dtoUser.getPhoneNumber());
        user.setContact(contact);
        user.setName(dtoUser.getName());
    }
}
