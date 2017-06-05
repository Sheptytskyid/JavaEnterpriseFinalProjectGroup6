package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data transfer object to handle {@link net.greatstart.model.User} registration.
 */

@Data
public class DtoUser {

    @NotNull
    @Pattern(regexp = "|^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
            + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{email.valid}")
    private String email;
    @NotNull
    @Size(min = 5, max = 15, message = "{password.size}")
    private String password;
    @NotNull
    private String confirmPassword;

}