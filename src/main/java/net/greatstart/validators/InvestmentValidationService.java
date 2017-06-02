package net.greatstart.validators;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProjectService projectService;

    @Autowired
    public InvestmentValidationService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public InvestmentValidationService() {
    }

    public boolean validate(DtoInvestment dtoInvestment) {
        BigDecimal sum = dtoInvestment.getSum();
        DtoProject dtoProject = projectService.getDtoProjectById(dtoInvestment.getProject().getId());
        BigDecimal investedSum = dtoProject.getDtoInvestments()
                .stream().map(DtoInvestment::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal minInvestment = dtoProject.getDesc().getMinInvestment();
        BigDecimal maxInvestment = dtoProject.getDesc().getCost().add(investedSum.negate());
        if (sum.compareTo(minInvestment) < 0) {
            return false;
        }
        if (sum.compareTo(BigDecimal.ZERO) != 0
                && sum.divideAndRemainder(minInvestment)[1].compareTo(BigDecimal.ZERO) != 0) {
            return false;
        }
        return sum.compareTo(maxInvestment) <= 0;
    }
}
