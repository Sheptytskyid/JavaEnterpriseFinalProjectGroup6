package net.greatstart.validators;

import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

/**
 * {@link }
 *
 * @author Oleksii Polite Rudenko
 * @version 1.0
 */
public class InvestmentValidationService {
    private InvestmentValidationService() {
    }

    public static void validate(Errors errors, BigDecimal sum, User investor, Project currentProject) {
        if (errors.hasErrors()
                || sum.compareTo(currentProject.getDesc().getMinInvestment()) > 0
                || investor.getContact().getPhoneNumber() == null
                || investor.getContact().getPhoneNumber().isEmpty()) {
            //todo
        }

    }
}
