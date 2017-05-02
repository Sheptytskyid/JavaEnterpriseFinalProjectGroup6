package net.greatstart.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "investment_interests")
public class InvestmentInterest extends AbstractModel {

    @Column(name = "goal")
    private String investmentGoal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "sum")
    private BigDecimal amountInvestment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User investor;
}
