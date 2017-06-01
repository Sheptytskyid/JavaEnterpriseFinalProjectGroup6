package net.greatstart.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class DtoProject {
    private Long id;
    @Valid
    @NotNull
    private DtoProjectDescription desc;
    @Valid
    private DtoCategory category;

}
