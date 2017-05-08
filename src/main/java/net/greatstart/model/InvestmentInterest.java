package net.greatstart.model;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "investment_interests")
@Component
public class InvestmentInterest extends AbstractModel {

    @Column(name = "goal")
    private String investmentGoal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(name = "sum")
    private BigDecimal amountInvestment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User investor;
}
