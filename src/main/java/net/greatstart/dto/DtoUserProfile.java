package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DtoUserProfile {
    private Long id;
    @NotNull
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
