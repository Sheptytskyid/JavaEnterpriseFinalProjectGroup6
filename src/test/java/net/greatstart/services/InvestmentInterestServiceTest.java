package net.greatstart.services;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.model.InvestmentInterest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentInterestServiceTest {

    private static final long ID = 1;
    @Mock
    private InvestmentInterestDao investmentInterestDao;
    @InjectMocks
    private InvestmentInterestService invInterestService;
    private InvestmentInterest invInterest = new InvestmentInterest();

    @Test
    public void invokeInvestmentInterestDaoWhenSaveInvestmentInterest() {
        invInterestService.saveInterest(invInterest);
        verify(investmentInterestDao, times(1)).save(invInterest);
    }

    @Test
    public void invokeInvestmentInterestDaoWhenDeleteInvestmentInterest() {
        when(invInterestService.getInvestmentInterestById(ID)).thenReturn(invInterest);
        invInterestService.deleteInvestmentInterest(ID);
        verify(investmentInterestDao, times(1)).delete(invInterest);
    }

    @Test
    public void returnEntityWhenGetInvestmentInterestById() {
        when(investmentInterestDao.findOne(ID)).thenReturn(invInterest);
        InvestmentInterest result = invInterestService.getInvestmentInterestById(ID);
        assertEquals(invInterest, result);
    }

//    @Test
//    public void returnListOfInvestmentInterestWhenGetAll() {
//        List<InvestmentInterest> investmentInterests = new ArrayList<>();
//        investmentInterests.add(invInterest);
//        when(investmentInterestDao.findAll()).thenReturn(investmentInterests);
//        List<InvestmentInterest> result = invInterestService.getAllInvestmentInterest();
//        assertEquals(investmentInterests, result);
//    }
}
