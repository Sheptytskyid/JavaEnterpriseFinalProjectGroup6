package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Entity
@Table(name = "investments")
public class Investment extends AbstractModel {
    @Column(name = "date")
    private LocalDate dateOfInvestment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    private User inv;

    @Column(name = "sum")
    private BigDecimal sum;
}
