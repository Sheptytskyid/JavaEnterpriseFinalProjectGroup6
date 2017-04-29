package net.greatstart.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "investment_interests")
public class InvestmentInterest extends AbstractModel {

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private InvestmentGoal investmentGoal;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private BigDecimal amountInvestment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id", nullable = false)
    private User investor;
}
