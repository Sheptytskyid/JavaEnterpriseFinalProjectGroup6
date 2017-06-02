package net.greatstart.validators;

import net.greatstart.model.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static net.greatstart.MapperHelper.*;

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
        project = getFullTestProject(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
    }

    @Test(timeout = 2000)
    public void validateNormalInvestment() throws Exception {
        sum = new BigDecimal(200);
        Assert.assertEquals(null, investmentValidationService.validate(sum, project));
    }

    @Test(timeout = 2000)
    public void validateLessThenMinInvestment() throws Exception {
        sum = new BigDecimal(99);
        Assert.assertEquals(WRONG_MIN_VALUE + TEST_MIN_INVEST_1, investmentValidationService.validate(sum, project));
    }

    @Test(timeout = 2000)
    public void validateMoreThenMaxInvestment() throws Exception {
        sum = new BigDecimal(9900);
        Assert.assertEquals(WRONG_MAX_VALUE + "9600", investmentValidationService.validate(sum, project));
    }

    @Test(timeout = 2000)
    public void validateWrongStepInvestment() throws Exception {
        sum = new BigDecimal(2350);
        Assert.assertEquals(WRONG_STEP_VALUE + TEST_MIN_INVEST_1, investmentValidationService.validate(sum, project));
    }

}