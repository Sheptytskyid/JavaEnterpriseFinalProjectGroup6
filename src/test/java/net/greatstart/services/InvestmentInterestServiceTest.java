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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class InvestmentInterestServiceTest {

    private static final long ID = 1;
    @Mock
    private InvestmentInterestDao investmentInterestDao;
    @InjectMocks
    private InvestmentInterestService invInterestService;
    @Mock
    private InvestmentInterest invInterest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        invInterestService = new InvestmentInterestService(investmentInterestDao);
    }

    @Test(timeout = 2000)
    public void shouldReturnEntityWhenGetInvestmentInterestById() {
        when(investmentInterestDao.findOne(ID)).thenReturn(invInterest);
        InvestmentInterest result = invInterestService.getInvestmentInterestById(ID);
        assertEquals(invInterest, result);
    }

    @Test(timeout = 2000)
    public void shouldReturnTrueWhenCreateInvestmentInterest() {
        invInterestService.saveInvestmentInterest(invInterest);
        verify(investmentInterestDao, times(1)).save(invInterest);
    }

    @Test(timeout = 2000)
    public void shouldReturnTrueWhenDeleteInvestmentInterest() {
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
