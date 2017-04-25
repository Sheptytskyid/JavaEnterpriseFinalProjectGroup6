package net.greatstart.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Investment {

    private LocalDate dateOfInvestment;
    private Project project;
    private User inv;
    private BigDecimal sum;
}
