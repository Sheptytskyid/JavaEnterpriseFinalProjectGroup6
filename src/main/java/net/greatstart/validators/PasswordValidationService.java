package net.greatstart.validators;

import net.greatstart.dto.DtoPassword;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PasswordValidationService {

    public void validate(DtoPassword password, Errors errors) {
        if (!password.getPassword().equals(password.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",
                "passwords.do.not.match",
                "Passwords do not match");
        }
    }
}
