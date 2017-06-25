package net.greatstart.services;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.dto.DtoInterest;
import net.greatstart.mappers.InterestMapper;
import net.greatstart.model.InvestmentInterest;
import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static net.greatstart.MapperHelper.*;
import static net.greatstart.MapperHelper.getTestListOfInvInterest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentInterestServiceTest {
    @Mock
    private InvestmentInterestDao investmentInterestDao;
    @Mock
    private UserService userService;
    @Mock
    private InterestMapper interestMapper;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private InvestmentInterestService invInterestService;
    private DtoInterest dtoInterest;
    private InvestmentInterest interest;
    private User user;
    private List<InvestmentInterest> interests;
    private List<DtoInterest> dtoInterests;

    @Before
    public void setUp() {
        dtoInterest = getTestDtoInterest(TEST_INVEST_1);
        interest = getTestInvInterest(TEST_INVEST_1);
        user = getTestUser();
        interests = getTestListOfInvInterest(TEST_INVEST_1);
        dtoInterests = getTestListOfDtoInterests(TEST_INVEST_1);
    }

    @Test
    public void invokeInvestmentInterestDaoWhenSaveInvestmentInterest() {
        invInterestService.saveInterest(interest);
        verify(investmentInterestDao, times(1)).save(interest);
    }

    @Test
    public void saveInterestLikeNullParameterShouldReturnException() {
        interest = null;
        when(investmentInterestDao.save(interest)).thenReturn(null);
        InvestmentInterest result = invInterestService.saveInterest(interest);
        assertNull(result);
        verify(investmentInterestDao, times(1)).save(interest);
    }

    @Test
    public void getUserDtoInterestsByEmailShouldReturnList() {
        user.setInvestmentInterests(interests);
        when(userService.getUserByEmail(TEST_EMAIL)).thenReturn(user);
        when(interestMapper.fromInterestToDto(interest)).thenReturn(dtoInterest);
        assertTrue(dtoInterests.containsAll(invInterestService.getUserDtoInterestsByUserEmail(TEST_EMAIL)));
        verify(userService, times(1)).getUserByEmail(TEST_EMAIL);
    }

    @Test
    public void invokeUpdateInterestShouldReturnDtoInterest() {
        when(interestMapper.interestFromDto(dtoInterest)).thenReturn(interest);
        when(investmentInterestDao.save(interest)).thenReturn(interest);
        when(interestMapper.fromInterestToDto(interest)).thenReturn(dtoInterest);
        assertEquals(invInterestService.updateDtoInterest(dtoInterest), dtoInterest);
        verify(investmentInterestDao, times(1)).save(interest);
        verify(interestMapper).fromInterestToDto(interest);
    }

    @Test
    public void createNewInterestShouldReturnDtoInterest() {
        when(userService.getLoggedInUser()).thenReturn(user);
        when(categoryService.findCategoryByName(dtoInterest.getCategory().getName())).thenReturn(TEST_CATEGORY);
        when(interestMapper.interestFromDto(dtoInterest)).thenReturn(interest);
        when(investmentInterestDao.save(interest)).thenReturn(interest);
        when(interestMapper.fromInterestToDto(interest)).thenReturn(dtoInterest);
        DtoInterest result = invInterestService.createNewInterestFromDto(dtoInterest);
        assertEquals(dtoInterest, result);
        verify(userService, times(1)).getLoggedInUser();
        verify(categoryService, times(1)).findCategoryByName(dtoInterest.getCategory().getName());
        verify(interestMapper, times(1)).fromInterestToDto(interest);
        verify(investmentInterestDao, times(1)).save(interest);
    }

    @Test
    public void getDtoInterestByIdShouldReturnDtoInterest() {
        when(investmentInterestDao.findOne(TEST_ID)).thenReturn(interest);
        when(interestMapper.fromInterestToDto(interest)).thenReturn(dtoInterest);
        DtoInterest result = invInterestService.getDtoInterestById(TEST_ID);
        assertEquals(dtoInterest, result);
        verify(investmentInterestDao, times(1)).findOne(TEST_ID);
        verify(interestMapper, times(1)).fromInterestToDto(interest);
    }

    @Test
    public void getDtoInterestDByIdThatNotExistShouldReturnNull() {
        when(investmentInterestDao.findOne(TEST_ID)).thenReturn(null);
        DtoInterest result = invInterestService.getDtoInterestById(TEST_ID);
        assertNull(result);
        verify(investmentInterestDao, times(1)).findOne(TEST_ID);
    }

    @Test
    public void getAllDtoInterestsShouldReturnList() {
        when(investmentInterestDao.findAll()).thenReturn(interests);
        when(interestMapper.fromInterestToDto(interest)).thenReturn(dtoInterest);
        List<DtoInterest> result = invInterestService.getAllDtoInterest();
        assertEquals(dtoInterests, result);
        verify(investmentInterestDao, times(1)).findAll();
    }

    @Test
    public void getInvestmentInterestByIdShouldReturnInterest() {
        when(investmentInterestDao.findOne(TEST_ID)).thenReturn(interest);
        InvestmentInterest result = invInterestService.getInvestmentInterestById(TEST_ID);
        assertEquals(interest, result);
        verify(investmentInterestDao, times(1)).findOne(TEST_ID);
    }

    @Test
    public void invokeInterestDaoWhenDeleteInterest() {
        when(investmentInterestDao.findOne(TEST_ID)).thenReturn(interest);
        invInterestService.deleteInvestmentInterest(TEST_ID);
        verify(investmentInterestDao, times(1)).delete(interest);
    }
}
