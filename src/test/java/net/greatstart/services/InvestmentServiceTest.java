package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceTest {

    @Mock
    private InvestmentDao investmentDao;
    @Mock
    private Errors errors;
    @InjectMocks
    private InvestmentService investmentService;
    private Investment investment;

    @Before
    public void setUp() {
        investment = new Investment();
        investment.setSum(new BigDecimal(123));
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

    }

    @Test
    public void getAllInvestments() throws Exception {

    }
}
