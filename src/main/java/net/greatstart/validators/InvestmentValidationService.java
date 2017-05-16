package net.greatstart.validators;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvestmentValidationService {
    private InvestmentValidationService() {
    }

    public static String validate(BigDecimal sum, Project project) {

        BigDecimal investedSum = project.getInvestments()
                .stream().map(Investment::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal minInvestment = project.getDesc().getMinInvestment();
        BigDecimal maxInvestment = minInvestment.add(investedSum);

        if (sum.compareTo(minInvestment) < 0) {
            return "You entered wrong investment value. Max possible investment is: $" + minInvestment;
        }

        if (sum.compareTo(maxInvestment) > 0) {
            return "You entered wrong investment value. Max possible investment is: $" + maxInvestment;
        }


        return null;
    }
}
