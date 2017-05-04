package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.model.InvestmentInterest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class InvestmentInterestServiceTest {

    private static final long ID = 1;

    @Mock
    InvestmentInterestDao investmentInterestDao;

    @InjectMocks
    InvestmentInterestService invInterestService;

    @Mock
    InvestmentInterest invInterest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        invInterestService = new InvestmentInterestService(investmentInterestDao);
    }

    @Test(timeout = 2000)
    public void shouldReturnEntityWhenGetInvestmentInterestById() {
        when(investmentInterestDao.getById(ID)).thenReturn(invInterest);
        InvestmentInterest result = invInterestService.getInvestmentInterestById(ID);
        assertEquals(invInterest, result);
    }

    @Test(timeout = 2000)
    public void shouldReturnTrueWhenCreateInvestmentInterest() {
        when(investmentInterestDao.create(invInterest)).thenReturn(true);
        boolean result = invInterestService.createInvestmentInterest(invInterest);
        assertTrue(result);
    }

    @Test(timeout = 2000)
    public void shouldReturnTrueWhenDeleteInvestmentInterest() {
        when(investmentInterestDao.getById(ID)).thenReturn(invInterest);
        when(investmentInterestDao.delete(invInterest)).thenReturn(true);
        boolean result = invInterestService.deleteInvestmentInterest(ID);
        assertTrue(result);
    }

    @Test(timeout = 2000)
    public void shouldReturnTrueWhenUpdateInvestmentInterest() {
        when(investmentInterestDao.getById(ID)).thenReturn(invInterest);
        when(investmentInterestDao.update(invInterest)).thenReturn(true);
        InvestmentInterest invInterestResult = invInterestService.getInvestmentInterestById(ID);
        invInterestResult.setId(2L);
        boolean result = invInterestService.updateInvestmentInterest(invInterestResult);
        assertTrue(result);
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
        when(investmentInterestDao.getAll()).thenReturn(investmentInterests);
        List<InvestmentInterest> result = invInterestService.getAllInvestmentInterest();
        assertEquals(investmentInterests, result);
    }
}
