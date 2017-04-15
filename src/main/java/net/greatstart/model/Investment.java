package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Entity
@Table(name = "investments")
public class Investment extends AbstractModel {
    @Column(name = "date")
    private LocalDate dateOfInvestment;
    @OneToOne(cascade = CascadeType.ALL)
    private Project project;

    @OneToOne(cascade = CascadeType.ALL)
    private User inv;

    @Column(name = "sum")
    private BigDecimal sum;
}
