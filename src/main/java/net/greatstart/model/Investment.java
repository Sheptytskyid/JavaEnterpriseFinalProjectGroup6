package net.greatstart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "investments")
public class Investment extends AbstractModel {
    @Column(name = "date")
    private LocalDate dateOfInvestment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User inv;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "paid")
    private boolean paid;
}
