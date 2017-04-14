package net.greatstart.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class Investment {

    private LocalDate dateOfInvestment;
    private Project project;
    private User inv;
    private BigDecimal sum;
}
