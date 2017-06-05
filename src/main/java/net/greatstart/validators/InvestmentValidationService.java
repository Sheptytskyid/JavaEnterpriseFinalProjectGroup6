/**
 * A utility class to validate {@link net.greatstart.dto.DtoInvestment}.
 */

package net.greatstart.validators;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
