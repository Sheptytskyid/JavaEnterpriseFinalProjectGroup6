package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.dto.DtoInvestment;
import net.greatstart.mappers.InvestmentMapper;
import net.greatstart.model.Investment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static net.greatstart.MapperHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceTest {

    @Mock
    private InvestmentDao investmentDao;
    @Mock
    private InvestmentMapper investmentMapper;
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @InjectMocks
    private InvestmentService investmentService;

    private Investment investment;
    private DtoInvestment dtoInvestment;
    private List<Investment> investments;
    private List<DtoInvestment> dtoInvestments;

    @Before
    public void setUp() {
        investment = getTestInvestment(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        dtoInvestment = getTestDtoInvestment(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        investments = getTestListOfInvestments(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        dtoInvestments = getTestListOfDtoInvestments(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
    }

    @Test
    public void saveInvestment() throws Exception {
        //init
        when(investmentMapper.investmentFromDto(dtoInvestment)).thenReturn(investment);
        when(projectService.getProjectById(TEST_VALUE_1))
                .thenReturn(getTestProject(TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1));
        when(userService.getUser(TEST_VALUE_1)).thenReturn(getTestUser());
        when(investmentDao.save(investment)).thenReturn(investment);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        //use & verify
        assertEquals(dtoInvestment, investmentService.saveInvestment(dtoInvestment));

        verify(investmentDao, times(1)).save(investment);
    }

    @Test
    public void deleteInvestment() throws Exception {
        investmentService.deleteInvestment(1L);
        verify(investmentDao, times(1)).delete(1L);
    }

    @Test
    public void getInvestmentById() throws Exception {
        when(investmentDao.findOne((long) TEST_VALUE_1)).thenReturn(investment);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        assertEquals(dtoInvestment, investmentService.getDtoInvestmentById(TEST_VALUE_1));
        verify(investmentDao, times(1)).findOne((long) TEST_VALUE_1);
    }

    @Test
    public void getAllDtoInvestments() throws Exception {
        when(investmentDao.findAll()).thenReturn(investments);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        assertEquals(dtoInvestments, investmentService.getAllDtoInvestments());
//        assertTrue(dtoInvestments.containsAll(investmentService.getAllDtoInvestments()));
        verify(investmentDao, times(1)).findAll();
    }
}
