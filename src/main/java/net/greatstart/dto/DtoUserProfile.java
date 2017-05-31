package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class DtoUserProfile {
    private Long id;
    @NotNull
    @Size(min = 5, max = 35, message = "{name.size}")
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String lastName;
    private byte[] photo;
    private String initial;
    private List<DtoInvestment> dtoInvestments;
}
