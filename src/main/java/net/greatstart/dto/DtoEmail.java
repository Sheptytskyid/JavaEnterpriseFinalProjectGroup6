package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DtoEmail {

    @NotNull
    @Pattern(regexp = "|^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
        + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{email.valid}")
    private String email;
}
