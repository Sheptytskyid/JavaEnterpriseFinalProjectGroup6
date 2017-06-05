package net.greatstart.dto;

import lombok.Data;
import net.greatstart.model.Category;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Data transfer object for {@link net.greatstart.model.Project}
 */

@Data
public class DtoProject {
    private Long id;
    @Valid
    @NotNull
    private DtoProjectDescription desc;
    @Valid
    private Category category;
    private DtoUserProfile owner;

}
