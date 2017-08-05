package net.greatstart.controllers;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.dto.DtoInterest;
import net.greatstart.mappers.InterestMapper;
import net.greatstart.mappers.InvestmentMapper;
import net.greatstart.model.Category;
import net.greatstart.model.InvestmentInterest;
import net.greatstart.services.CategoryService;
import net.greatstart.services.InvestmentInterestService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import static net.greatstart.JsonConverter.convertObjectToJsonBytes;
import static net.greatstart.MapperHelper.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentInterestControllerTest {

    private static final long ID = 1L;
    @Mock
    private InvestmentInterestService interestService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private UserService userService;
    @Mock
    private InvestmentInterestDao investmentInterestDao;
    @InjectMocks
    private InvestmentInterestController controller;
    @Mock
    private Principal principal;
    @Mock
    private InterestMapper interestMapper;
    @Mock
    private Category category;
    private MockMvc mvc;
    private DtoInterest dtoInterest;
    private List<DtoInterest> dtoInterests;
    private InvestmentInterest interest;
    @Before
    public void setup() throws Exception {
        mvc = standaloneSetup(controller).build();
        dtoInterests = getTestListOfDtoInterests(TEST_INVEST_1);
        dtoInterest = getTestDtoInterest(TEST_INVEST_1);
        interest = getTestInvInterest(TEST_INVEST_1);
    }

    @Test
    public void getInterestByIdShouldReturnOkHttpStatus() throws Exception {
        when(interestService.getDtoInterestById(TEST_ID)).thenReturn(dtoInterest);
        mvc.perform(get("/api/interest/" + TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInterest)))
                .andExpect(status().isOk());
        verify(interestService).getDtoInterestById(TEST_ID);
    }

    @Test
    public void saveInterestShouldReHttpStatusOk() throws Exception {
        when(interestService.createNewInterestFromDto(dtoInterest)).thenReturn(dtoInterest);
        mvc.perform(post("/api/interest")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertObjectToJsonBytes(dtoInterest)))
        .andExpect(status().isOk());
        verify(interestService).createNewInterestFromDto(dtoInterest);
    }

    @Test
    public void saveInterestWithEmptyCategoryShouldReturnOkHttpStatus() throws Exception {
        String name = "";
        when(categoryService.findCategoryByName(name)).thenReturn(category);
        when(interestService.createNewInterestFromDto(dtoInterest)).thenReturn(dtoInterest);
        mvc.perform(post("/api/interest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInterest)))
                .andExpect(status().isOk());
    }

    @Test
    public void getNotExistingInterestByIdExpectedNotFound() throws Exception {
        mvc.perform(get("/api/interest/" + TEST_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateInterestWithWrongUserShouldExpectForbidden() throws Exception {
        dtoInterest.getInvestor().setId(-1L);
        when(userService.getLoggedInUser()).thenReturn(getTestUser());
        mvc.perform(put("/api/interest/" + TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInterest)))
                .andExpect(status().isForbidden());
        verify(userService).getLoggedInUser();
    }

    @Test
    public void updateInterestWithCorrectUserCouldNotSaveExpectNotFound() throws Exception {
        when(userService.getLoggedInUser()).thenReturn(getTestUser());
        mvc.perform(put("/api/interest/" + TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInterest)))
                .andExpect(status().isNotFound());
        verify(userService).getLoggedInUser();
    }

    @Test
    public void updateInterestShouldReturnOkHttpStatus() throws Exception {
        when(userService.getLoggedInUser()).thenReturn(getTestUser());
        when(interestService.updateDtoInterest(dtoInterest)).thenReturn(dtoInterest);
        mvc.perform(put("/api/interest/" + TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInterest)))
                .andExpect(status().isOk());
        verify(interestService).updateDtoInterest(dtoInterest);
        verifyNoMoreInteractions(interestService);
    }

    @Test
    public void getMyInterestsShouldReturnOkHttpStatus() throws Exception {
        when(principal.getName()).thenReturn(TEST_EMAIL);
        when(interestService.getUserDtoInterestsByUserEmail(TEST_EMAIL)).thenReturn(dtoInterests);
        mvc.perform(get("/api/interest/my")
                .principal(principal))
                .andExpect(status().isOk());
        verify(interestService).getUserDtoInterestsByUserEmail(TEST_EMAIL);
        verifyNoMoreInteractions(interestService);
    }

    @Test
    public void getAllInterestsShouldReturnOkHttpStatus() throws Exception {
        when(interestService.getDtoInterestsFromInterests(getTestListOfInvInterest(TEST_INVEST_1))).thenReturn(dtoInterests);
        mvc.perform(get("/api/interest"))
                .andExpect(status().isOk());
        verify(interestService).getAllDtoInterest();
    }

    @Test
    public void deleteInterestByIdShouldReturnOk() throws Exception {
        when(investmentInterestDao.findOne(TEST_ID)).thenReturn(interest);
        when(interestService.getDtoInterestById(TEST_ID)).thenReturn(dtoInterest);
        mvc.perform(delete("/api/interest/" + TEST_ID))
                .andExpect(status().isOk());
        verify(interestService, times(1)).getDtoInterestById(TEST_ID);
    }

    @Test
    public void deleteInterestWithWrongIdShouldReturnNotFound() throws Exception {
        when(interestService.getDtoInterestById(TEST_ID)).thenReturn(null);
        mvc.perform(delete("/api/interest/" + TEST_ID))
                .andExpect(status().isNotFound());
        verify(interestService, times(1)).getDtoInterestById(TEST_ID);
    }

}
