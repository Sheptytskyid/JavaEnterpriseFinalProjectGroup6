package net.greatstart.validators;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * A utility class to validate {@link net.greatstart.dto.DtoInvestment}.
 */

/**
 * A utility class to validate {@link net.greatstart.dto.DtoInvestment}.
 */

@Component
public class InvestmentValidationService {

    private ProjectService projectService;

    @Autowired
    public InvestmentValidationService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public InvestmentValidationService() {
    }

    public boolean validate(DtoInvestment dtoInvestment) {
        Project project = projectService.getProjectById(dtoInvestment.getProject().getId());
        List<Investment> investments = project.getInvestments();
        BigDecimal investedSum = new BigDecimal(0);
        for (Investment investment : investments) {
            if (investment.isPaid()) {
                investedSum = investedSum.add(investment.getSum());
            }
        }

        BigDecimal sum = dtoInvestment.getSum();
        BigDecimal minInvestment = project.getDesc().getMinInvestment();
        BigDecimal maxInvestment = project.getDesc().getCost().add(investedSum.negate());
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
