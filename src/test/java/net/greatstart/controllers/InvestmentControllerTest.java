package net.greatstart.controllers;

import net.greatstart.dto.DtoProject;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.Investment;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.validators.InvestmentValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentControllerTest {

    @Mock
    private InvestmentValidationService investmentValidationService;
    @Mock
    private Principal principal;
    @Mock
    private InvestmentService investmentService;
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @InjectMocks
    private InvestmentController investmentController;
    private User user;
    private DtoUserProfile dtoUser;
    private DtoProject project;
    private List<Investment> investments;
    private MockMvc mvc;
    @Captor
    private ArgumentCaptor<Investment> captor;

    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(investmentController).build();
    }

    @Test(timeout = 2000)
    public void saveValidInvestmentShouldInvokeSaveServiceMethodAndRedirectToProjectPage() throws Exception {
        /*Investment investment = getTestInvestment(project, user, new BigDecimal(2000));
        when(principal.getName()).thenReturn(TEST_EMAIL);
        when(userService.getUserByEmail(TEST_EMAIL)).thenReturn(user);
        when(projectService.getProjectById(1)).thenReturn(project);
        when(investmentValidationService.validate(new BigDecimal(0), project))
                .thenReturn(null);
        mvc.perform(post("/project/1/investment/add")
                .principal(principal)
                .param("sum", "2000"))
                .andExpect(view().name(REDIRECT + PROJECT_PAGE + 1));

        verify(investmentService, times(1)).saveInvestment(captor.capture());
        assertEquals(investment.getSum(), captor.getValue().getSum());
        assertEquals(investment.getInvestor(), captor.getValue().getInvestor());
        assertEquals(investment.getProject(), captor.getValue().getProject());
        assertTrue((investment.getDateOfInvestment()
                .compareTo(captor.getValue().getDateOfInvestment()) <= 1)
                && (investment.getDateOfInvestment()
                .compareTo(captor.getValue().getDateOfInvestment()) >= -1));
        verifyNoMoreInteractions(investmentService);*/
    }

    @Test(timeout = 2000)
    public void saveInvalidInvestmentShouldReturnToAddInvestmentPage() throws Exception {
        /*when(principal.getName()).thenReturn(TEST_EMAIL);
        when(userService.getUserByEmail(TEST_EMAIL)).thenReturn(user);
        when(projectService.getProjectById(1)).thenReturn(project);
        when(investmentValidationService.validate(new BigDecimal(2500), project))
                .thenReturn("Some error.");
        mvc.perform(post("/project/1/investment/add")
                .principal(principal)
                .param("sum", "2500"))
                .andExpect(view().name("investment/add_investment"))
                .andExpect(model().attribute("message", "Some error."))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("investedSum", new BigDecimal(5000)));
        verifyNoMoreInteractions(investmentService);*/
    }

    @Test(timeout = 2000)
    public void getAllInvestmentsShouldReturnOkRequestAndListOfInvestments() throws Exception {
        /*when(investmentService.getAllInvestments()).thenReturn(investments);
        mvc.perform(get("/api/investment"))
                .andExpect();*/
    }

    @Test(timeout = 2000)
    public void getInvestmentById() throws Exception {
        /*mvc.perform(get("/api/investment/1"));
        verify(investmentService).getDtoInvestmentById(1L);
        verifyNoMoreInteractions(investmentService);*/
    }
}