package net.greatstart.controllers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.services.InvestmentService;
import net.greatstart.validators.InvestmentValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static net.greatstart.JsonConverter.convertObjectToJsonBytes;
import static net.greatstart.MapperHelper.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentControllerTest {

    @Mock
    private InvestmentValidationService investmentValidationService;
    @Mock
    private InvestmentService investmentService;
    @InjectMocks
    private InvestmentController investmentController;

    private DtoInvestment dtoInvestment;
    private List<DtoInvestment> dtoInvestments;
    private MockMvc mvc;

    @Captor
    private ArgumentCaptor<DtoInvestment> captor;

    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(investmentController).build();
        dtoInvestments = getTestListOfDtoInvestments(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        dtoInvestment = getTestDtoInvestment(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
    }

    @Test(timeout = 2000)
    public void saveValidInvestmentShouldPassValidationAndInvokeSaveServiceMethodAndReturnHttpStatusOk()
            throws Exception {
        //init
        dtoInvestment.setId(1L);
        dtoInvestment.setDateOfInvestment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        when(investmentValidationService.validate(dtoInvestment)).thenReturn(true);
        when(investmentService.saveInvestment(dtoInvestment)).thenReturn(dtoInvestment);
        //use & check
        mvc.perform(post("/api/investment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInvestment)))
                .andExpect(status().isOk());
        verify(investmentService, times(1)).saveInvestment(dtoInvestment);
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 4000)
    public void saveInvalidInvestmentShouldReturnExpectationFailedHttpStatus() throws Exception {
        //init
        dtoInvestment.setDateOfInvestment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        when(investmentValidationService.validate(dtoInvestment)).thenReturn(false);
        when(investmentService.saveInvestment(dtoInvestment)).thenReturn(dtoInvestment);
        //use & check
        mvc.perform(post("/api/investment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dtoInvestment)))
                .andExpect(status().isExpectationFailed());
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void getAllInvestmentsShouldReturnOkRequestIfAtLeastOneInvestmentExists() throws Exception {
        when(investmentService.getAllDtoInvestments()).thenReturn(dtoInvestments);
        mvc.perform(get("/api/investment"))
                .andExpect(status().isOk());
        verify(investmentService, times(1)).getAllDtoInvestments();
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void getAllInvestmentsShouldReturnNotFoundRequestIfThereIsNoInvestment() throws Exception {
        List<DtoInvestment> emptyList = new ArrayList<>();
        when(investmentService.getAllDtoInvestments()).thenReturn(emptyList);
        mvc.perform(get("/api/investment"))
                .andExpect(status().isNotFound());
        verify(investmentService, times(1)).getAllDtoInvestments();
        verifyNoMoreInteractions(investmentService);

    }

    @Test(timeout = 2000)
    public void getInvestmentByValidIdShouldReturnHttpStatusOk() throws Exception {
        when(investmentService.getDtoInvestmentById(1L)).thenReturn(dtoInvestment);
        mvc.perform(get("/api/investment/1"))
                .andExpect(status().isOk());
        verify(investmentService, times(1)).getDtoInvestmentById(TEST_VALUE_1);
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void getInvestmentByInvalidIdShouldReturnHttpStatusBadRequest() throws Exception {
        when(investmentService.getDtoInvestmentById(12L)).thenReturn(null);
        mvc.perform(get("/api/investment/12"))
                .andExpect(status().isNotFound());
        verify(investmentService, times(1)).getDtoInvestmentById(12);
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void deleteInvestmentByValidIdShouldReturnHttpStatusOk() throws Exception {
        when(investmentService.getDtoInvestmentById(1L)).thenReturn(dtoInvestment);
        mvc.perform(delete("/api/investment/1"))
                .andExpect(status().isOk());
        verify(investmentService, times(1)).getDtoInvestmentById(1);
        verify(investmentService, times(1)).deleteInvestment(1);
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void deleteInvestmentByInvalidIdShouldReturnHttpStatusBadRequest() throws Exception {
        when(investmentService.getDtoInvestmentById(12L)).thenReturn(null);
        mvc.perform(delete("/api/investment/12"))
                .andExpect(status().isNotFound());
        verify(investmentService, times(1)).getDtoInvestmentById(12);
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void updateInvestment() throws Exception {
        mvc.perform(put("/api/investment/1"))
                .andExpect(status().isBadRequest());
        verifyNoMoreInteractions(investmentService);
    }

}