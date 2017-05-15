package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DtoPassword {

    @NotNull
    @Size(min = 5, max = 35, message = "{password.size}")
    private String password;
    @NotNull
    private String confirmPassword;
}
