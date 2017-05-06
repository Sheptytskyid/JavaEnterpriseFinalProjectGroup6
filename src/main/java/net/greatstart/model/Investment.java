package net.greatstart.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "investments")
public class Investment extends AbstractModel {
    @Column(name = "date")
    private LocalDate dateOfInvestment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn (name = "user_id")
    private User investor;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "paid")
    private boolean paid;
}
