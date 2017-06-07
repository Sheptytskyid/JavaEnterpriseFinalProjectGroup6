package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.dto.DtoInvestment;
import net.greatstart.mappers.InvestmentMapper;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static net.greatstart.MapperHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    private Project project;
    private User user;

    @Before
    public void setUp() {
        user = getTestUser();
        project = getTestProject();
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
                .thenReturn(project);
        when(userService.getUser(TEST_VALUE_1)).thenReturn(getTestUser());
        when(investmentDao.save(investment)).thenReturn(investment);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        //use & verify
        assertEquals(dtoInvestment, investmentService.saveInvestment(dtoInvestment));

        verify(investmentDao, times(1)).save(investment);
        verify(projectService, times(1)).getProjectById(TEST_VALUE_1);
        verify(userService, times(1)).getUser(TEST_VALUE_1);
    }

    @Test
    public void updateValidInvestmentShouldReturnDtoInvestment() throws Exception {
        //init
        when(investmentService.getDtoInvestmentById(TEST_VALUE_1)).thenReturn(dtoInvestment);
        when(investmentMapper.investmentFromDto(dtoInvestment)).thenReturn(investment);
        when(projectService.getProjectById(TEST_VALUE_1))
                .thenReturn(project);
        when(userService.getUser(TEST_VALUE_1)).thenReturn(getTestUser());
        when(investmentDao.save(investment)).thenReturn(investment);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        //use & verify
        assertEquals(dtoInvestment, investmentService.updateInvestment(dtoInvestment));
        verify(investmentDao, times(1)).save(investment);
        verify(projectService, times(1)).getProjectById(TEST_VALUE_1);
        verify(userService, times(1)).getUser(TEST_VALUE_1);
    }

    @Test
    public void updateInvalidInvestmentShouldNull() throws Exception {
        //init
        when(investmentService.getDtoInvestmentById(TEST_VALUE_1)).thenReturn(null);
        //use & verify
        assertEquals(null, investmentService.updateInvestment(dtoInvestment));
        verify(investmentDao, times(0)).save(investment);
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
        //init
        when(investmentDao.findAll()).thenReturn(investments);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        //use & check
        assertTrue(dtoInvestments.containsAll(investmentService.getAllDtoInvestments()));
        verify(investmentDao, times(1)).findAll();
    }

    @Test
    public void getProjectDtoInvestments() throws Exception {
        //init
        project.setInvestments(investments);
        when(projectService.getProjectById((long)TEST_VALUE_1)).thenReturn(project);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        //use & check
        assertTrue(dtoInvestments.containsAll(investmentService.getDtoProjectInvestments((long)TEST_VALUE_1)));
        verify(projectService, times(1)).getProjectById((long)TEST_VALUE_1);
    }

    @Test
    public void getUserDtoInvestments() throws Exception {
        //init
        user.setInvestments(investments);
        when(userService.getUserByEmail(TEST_EMAIL)).thenReturn(user);
        when(investmentMapper.fromInvestmentToDto(investment)).thenReturn(dtoInvestment);
        //use & check
        assertTrue(dtoInvestments.containsAll(investmentService.getUserDtoInvestmentsByUserEmail(TEST_EMAIL)));
        verify(userService, times(1)).getUserByEmail(TEST_EMAIL);
    }
}
