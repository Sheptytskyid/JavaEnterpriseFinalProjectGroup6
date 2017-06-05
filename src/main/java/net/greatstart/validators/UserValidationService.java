package net.greatstart.validators;

import net.greatstart.dto.DtoUser;
import net.greatstart.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * A utility class to validate {@link net.greatstart.dto.DtoUser} when registering
 * a new {@link net.greatstart.model.User}.
 */

@Component
public class UserValidationService {

    public void validate(DtoUser user, Errors errors, UserService userService) {
        if (user != null) {
            if (userService.getUserByEmail(user.getEmail()) != null) {
                errors.rejectValue("email",
                        "user.exists",
                        "A user with such email already exists");
            }
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword",
                        "passwords.do.not.match",
                        "Passwords do not match");
            }
        }
    }
}
