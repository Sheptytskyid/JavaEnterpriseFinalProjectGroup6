package net.greatstart.dto;

import lombok.Data;
import net.greatstart.model.Category;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DtoProject {
    private Long id;
    @Valid
    @NotNull
    private DtoProjectDescription desc;
    @Valid
    private Category category;
    private DtoUserProfile owner;

    private List<DtoInvestment> dtoInvestments;

}
