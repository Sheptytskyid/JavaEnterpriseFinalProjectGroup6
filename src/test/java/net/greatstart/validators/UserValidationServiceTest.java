package net.greatstart.validators;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.User;
import net.greatstart.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserValidationServiceTest {

    private static final String EMAIL = "test@test.com";
    private static final String PASS = "pass";
    private static final String CONF_PASS = "anotherPass";

    @Mock
    private UserService userService;
    @Mock
    private Errors errors;
    @InjectMocks
    private UserValidationService userValidationService;

    @Test
    public void validate() throws Exception {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setEmail(EMAIL);
        dtoUser.setPassword(PASS);
        dtoUser.setConfirmPassword(CONF_PASS);
        when(userService.getUserByEmail(dtoUser.getEmail())).thenReturn(new User());
        userValidationService.validate(dtoUser, errors, userService);
        verify(errors, times(1)).rejectValue("email",
            "user.exists",
            "A user with such email already exists");
        verify(errors, times(1)).rejectValue("confirmPassword",
            "passwords.do.not.match",
            "Passwords do not match");
    }
}
