package net.greatstart.validators;

import net.greatstart.dto.DtoUser;
import net.greatstart.services.UserService;
import org.springframework.validation.Errors;

public class NewUserValidator {

    public static void validate(DtoUser user, Errors errors, UserService userService) {
        if (user != null) {
            if (userService.getUserByEmail(user.getUsername()) != null) {
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
