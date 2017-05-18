package net.greatstart.validators;

import net.greatstart.dto.DtoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class PasswordValidationServiceTest {

    private DtoUser dtoUser = new DtoUser();
    private Errors errors = new BindException(dtoUser, "user");
    @InjectMocks
    private PasswordValidationService service;

    @Test
    public void validate() throws Exception {
        dtoUser.setPassword("1");
        dtoUser.setConfirmPassword("2");
        service.validate(dtoUser, errors);
        assertTrue(errors.hasErrors());
    }
}
