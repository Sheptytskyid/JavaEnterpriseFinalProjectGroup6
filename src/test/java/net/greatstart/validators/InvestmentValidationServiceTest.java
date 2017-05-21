package net.greatstart.validators;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentValidationServiceTest {

    private static final String MESSAGE = "You entered wrong investment value. ";
    private static final String WRONG_MIN_VALUE = MESSAGE
            + "Min possible investment is: $";
    private static final String WRONG_MAX_VALUE = MESSAGE
            + "Max possible investment is: $";
    private static final String WRONG_STEP_VALUE = MESSAGE
            + "Note, your investment must be multiple $";

    private Project project;
    private BigDecimal sum;
    private InvestmentValidationService investmentValidationService;

    @Before
    public void setUp() throws Exception {
        investmentValidationService = new InvestmentValidationService();
        project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setMinInvestment(new BigDecimal(1000));
        desc.setCost(new BigDecimal(10000));
        project.setDesc(desc);
        Investment investment1 = new Investment();
        investment1.setSum(new BigDecimal(2000));
        Investment investment2 = new Investment();
        investment2.setSum(new BigDecimal(3000));
        List<Investment> investments = Arrays.asList(investment1, investment2);
        project.setInvestments(investments);
    }

    @Test(timeout = 2000)
    public void validateNormalInvestment() throws Exception {
        sum = new BigDecimal(1000);
        Assert.assertEquals(null, investmentValidationService.validate(sum, project));
    }

    @Test(timeout = 2000)
    public void validateLessThenMinInvestment() throws Exception {
        sum = new BigDecimal(999);
        Assert.assertEquals(WRONG_MIN_VALUE + "1000", investmentValidationService.validate(sum, project));
    }

    @Test(timeout = 2000)
    public void validateMoreThenMaxInvestment() throws Exception {
        sum = new BigDecimal(6000);
        Assert.assertEquals(WRONG_MAX_VALUE + "5000", investmentValidationService.validate(sum, project));
    }

    @Test(timeout = 2000)
    public void validateWrongStepInvestment() throws Exception {
        sum = new BigDecimal(2200);
        Assert.assertEquals(WRONG_STEP_VALUE + "1000", investmentValidationService.validate(sum, project));
    }

}