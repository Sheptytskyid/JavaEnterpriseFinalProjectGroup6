package net.greatstart.validators;

import net.greatstart.dto.DtoUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PasswordValidationService {

    public void validate(DtoUser user, Errors errors) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",
                "passwords.do.not.match",
                "Passwords do not match");
        }
    }
}
