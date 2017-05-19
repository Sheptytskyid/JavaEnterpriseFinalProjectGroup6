package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceTest {

    @Mock
    private InvestmentDao investmentDao;
    @InjectMocks
    private InvestmentService investmentService;
    private Investment investment;
    private List<Investment> investments;

    @Before
    public void setUp() {
        investment = new Investment();
        investment.setSum(new BigDecimal(123));
        investments = new ArrayList<>();
        investments.add(investment);
    }

    @Test
    public void saveInvestment() throws Exception {
        when(investmentDao.save(investment)).thenReturn(investment);
        assertEquals(investment, investmentService.saveInvestment(investment));
        verify(investmentDao, times(1)).save(investment);
    }

    @Test
    public void deleteInvestment() throws Exception {
        investmentService.deleteInvestment(1L);
        verify(investmentDao,times(1)).delete(1L);
    }

    @Test
    public void getInvestmentById() throws Exception {
        when(investmentDao.findOne(1L)).thenReturn(investment);
        assertEquals(investment, investmentService.getInvestmentById(1L));
        verify(investmentDao, times(1)).findOne(1L);
    }

    @Test
    public void getAllInvestments() throws Exception {
        when(investmentDao.findAll()).thenReturn(investments);
        assertEquals(investments, investmentService.getAllInvestments());
        verify(investmentDao, times(1)).findAll();
    }
}
