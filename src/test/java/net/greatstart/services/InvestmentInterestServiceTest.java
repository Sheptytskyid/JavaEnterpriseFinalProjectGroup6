package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.model.InvestmentInterest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Main.class)
public class InvestmentInterestServiceTest {

    private static final long ID = 1;
    @Mock
    private InvestmentInterestDao investmentInterestDao;
    @Mock
    private InvestmentInterest invInterest;
    @InjectMocks
    private InvestmentInterestService invInterestService;

    @Test(timeout = 2000)
    public void shouldReturnEntityWhenGetInvestmentInterestById() {
        when(investmentInterestDao.findOne(ID)).thenReturn(invInterest);
        InvestmentInterest result = invInterestService.getInvestmentInterestById(ID);
        assertEquals(invInterest, result);
    }

    @Test(timeout = 2000)
    public void shouldInvokeInvestmentInterestDaoWhenCreateInvestmentInterest() {
        invInterestService.saveInvestmentInterest(invInterest);
        verify(investmentInterestDao, times(1)).save(invInterest);
    }

    @Test(timeout = 2000)
    public void shouldInvokeInvestmentInterestDaoWhenDeleteInvestmentInterest() {
        investmentInterestDao.findOne(ID);
        verify(investmentInterestDao, times(1)).findOne(ID);
    }

    @Test(timeout = 2000)
    public void shouldReturnListOfInvestmentInterestWhenCallMethodGetAll() {
        List<InvestmentInterest> investmentInterests = new ArrayList<>();
        InvestmentInterest investmentInterestOne = mock(InvestmentInterest.class);
        InvestmentInterest investmentInterestTwo = mock(InvestmentInterest.class);
        InvestmentInterest investmentInterestThree = mock(InvestmentInterest.class);
        investmentInterests.add(investmentInterestOne);
        investmentInterests.add(investmentInterestTwo);
        investmentInterests.add(investmentInterestThree);
        when(investmentInterestDao.findAll()).thenReturn(investmentInterests);
        List<InvestmentInterest> result = invInterestService.getAllInvestmentInterest();
        assertEquals(investmentInterests, result);
    }
}
