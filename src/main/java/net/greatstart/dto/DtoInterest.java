package net.greatstart.dto;


import lombok.Data;
import net.greatstart.model.Category;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.Valid;
import java.math.BigDecimal;

@Data
public class DtoInterest {
    long id;
    String investmentGoal;
    @Valid
    Category category;
    String description;
    @Valid
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    BigDecimal amountInvestment;
    DtoUserProfile investor;
}
