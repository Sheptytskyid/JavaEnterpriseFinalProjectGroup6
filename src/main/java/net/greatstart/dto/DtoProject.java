package net.greatstart.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DtoProject {
    private Long id;
    @Valid
    @NotNull
    private DtoCategory category;
    @Valid
    private DtoProjectDescription desc;
    private List<DtoInvestment> dtoInvestments;

}
