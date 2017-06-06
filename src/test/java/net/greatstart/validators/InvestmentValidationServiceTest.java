package net.greatstart.validators;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.model.Project;
import net.greatstart.services.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static net.greatstart.MapperHelper.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentValidationServiceTest {

    @Mock
    private ProjectService projectService;
    @InjectMocks
    private InvestmentValidationService investmentValidationService;

    private DtoProject dtoProject;
    private Project project;
    private DtoInvestment dtoInvestment;

    @Before
    public void setUp() throws Exception {
        dtoInvestment = getTestDtoInvestment(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        dtoProject = getTestDtoProject();
        project = getTestProject();
    }

    @Test(timeout = 2000)
    public void validateNormalInvestment() throws Exception {
        //init
        dtoInvestment.setSum(new BigDecimal(2000));
        when(projectService.getProjectById((long) TEST_VALUE_1)).thenReturn(project);
        //use & check
        assertTrue(investmentValidationService.validate(dtoInvestment));
    }

    @Test(timeout = 2000)
    public void validateLessThenMinInvestment() throws Exception {
        //init
        dtoInvestment.setSum(new BigDecimal(900));
        when(projectService.getProjectById((long) TEST_VALUE_1)).thenReturn(project);
        //use & check
        assertFalse(investmentValidationService.validate(dtoInvestment));
    }

    @Test(timeout = 2000)
    public void validateMoreThenMaxInvestment() throws Exception {
        //init
        dtoInvestment.setSum(new BigDecimal(9000));
        when(projectService.getProjectById((long)TEST_VALUE_1)).thenReturn(project);
        //use & check
        assertFalse(investmentValidationService.validate(dtoInvestment));
    }

    @Test(timeout = 2000)
    public void validateWrongStepInvestment() throws Exception {
        //init
        dtoInvestment.setSum(new BigDecimal(1500));
        when(projectService.getProjectById((long)TEST_VALUE_1)).thenReturn(project);
        //use & check
        assertFalse(investmentValidationService.validate(dtoInvestment));
    }

}