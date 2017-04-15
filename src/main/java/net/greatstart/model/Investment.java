package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Entity
@Table(name = "investments")
public class Investment  extends AbstractModel{

    private LocalDate dateOfInvestment;
    private Project project;
    private User inv;
    private BigDecimal sum;
}
