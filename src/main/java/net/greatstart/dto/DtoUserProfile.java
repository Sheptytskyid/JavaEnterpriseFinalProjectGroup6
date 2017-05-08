package net.greatstart.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Component
public class DtoUserProfile {
    private Long id;
    @NotNull
    @Size(min = 5, max = 35, message = "{name.size}")
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
