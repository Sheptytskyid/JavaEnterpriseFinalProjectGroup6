/**
 * Data transfer object for {@link net.greatstart.model.Investment}.
 */
package net.greatstart.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoInvestment {
    long id;
    LocalDateTime dateOfInvestment;
    DtoProject project;
    DtoUserProfile investor;
    @NotNull
    BigDecimal sum;
    boolean verified;
    boolean paid;

}
