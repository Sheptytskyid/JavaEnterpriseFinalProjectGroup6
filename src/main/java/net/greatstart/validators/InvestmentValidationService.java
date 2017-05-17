package net.greatstart.validators;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvestmentValidationService {
    private static final String WRONG_MIN_VALUE = "You entered wrong investment value. "
            + "Min possible investment is: $";
    private static final String WRONG_MAX_VALUE = "You entered wrong investment value. "
            + "Max possible investment is: $";

    private InvestmentValidationService() {
    }

    public static String validate(BigDecimal sum, Project project) {

        BigDecimal investedSum = project.getInvestments()
                .stream().map(Investment::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal minInvestment = project.getDesc().getMinInvestment();
        BigDecimal maxInvestment = project.getDesc().getCost().add(investedSum.negate());
        if (sum.compareTo(minInvestment) < 0) {
            return WRONG_MIN_VALUE + minInvestment;
        }
        if (sum.compareTo(maxInvestment) > 0) {
            return WRONG_MAX_VALUE + maxInvestment;
        }
        return null;
    }
}
