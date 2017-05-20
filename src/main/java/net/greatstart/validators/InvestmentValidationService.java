package net.greatstart.validators;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvestmentValidationService {
    private static final String MESSAGE = "You entered wrong investment value. ";
    private static final String WRONG_MIN_VALUE = MESSAGE
            + "Min possible investment is: $";
    private static final String WRONG_MAX_VALUE = MESSAGE
            + "Max possible investment is: $";
    private static final String WRONG_STEP_VALUE = MESSAGE
            + "Note, your investment must be multiple $";

    public String validate(BigDecimal sum, Project project) {

        BigDecimal investedSum = project.getInvestments()
                .stream().map(Investment::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal minInvestment = project.getDesc().getMinInvestment();
        BigDecimal maxInvestment = project.getDesc().getCost().add(investedSum.negate());
        if (sum.compareTo(minInvestment) < 0) {
            return WRONG_MIN_VALUE + minInvestment;
        }
        if (sum.compareTo(BigDecimal.ZERO) != 0
                && sum.divideAndRemainder(minInvestment)[1].compareTo(BigDecimal.ZERO) != 0) {
            return WRONG_STEP_VALUE + minInvestment;
        }
        if (sum.compareTo(maxInvestment) > 0) {
            return WRONG_MAX_VALUE + maxInvestment;
        }
        return null;
    }
}
