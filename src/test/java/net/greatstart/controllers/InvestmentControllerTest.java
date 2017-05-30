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
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static net.greatstart.MapperHelper.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentControllerTest {
    private static final String REDIRECT = "redirect:/";
    private static final String PROJECT_PAGE = "project/";
    private static final String PAGE_NAME = "pageName";
    private static final String INVESTMENT_LIST = "investmentList";
    private static final String INVESTMENTS_VIEW = "investment/investments";

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
        dtoUser = getTestDtoUserProfile();
        user = getTestUser();
        project = getTestDtoProject();
        Investment investment1 = new Investment();
        investment1.setSum(new BigDecimal(3000));
        Investment investment2 = new Investment();
        investment2.setSum(new BigDecimal(2000));
        investments = Arrays.asList(investment1, investment2);
//        project.setInvestments(investments);
        user.setInvestments(investments);
    }
//    todo!!!
/*
    @Test(timeout = 2000)
    public void getAddInvestmentForm() throws Exception {
        when(projectService.getProjectById(1)).thenReturn(project);
        mvc.perform(get("/project/1/investment/add"))
                .andExpect(view().name("investment/add_investment"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("investedSum", new BigDecimal(5000)));
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void saveValidInvestmentShouldInvokeSaveServiceMethodAndRedirectToProjectPage() throws Exception {
        Investment investment = createInvestment(project, user, new BigDecimal(2000));
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
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void saveInvalidInvestmentShouldReturnToAddInvestmentPage() throws Exception {
        when(principal.getName()).thenReturn(TEST_EMAIL);
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
        verifyNoMoreInteractions(investmentService);
    }

    @Test(timeout = 2000)
    public void getAllInvestmentsShouldReturnPageWithAllInvestmentsFromAllProjects() throws Exception {
        when(investmentService.getAllInvestments()).thenReturn(investments);
        mvc.perform(get("/investment"))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "All investments."))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }

*//*    @Test(timeout = 2000)
    public void getAllProjectInvestmentsShouldReturnPageWithAllInvestmentsFromIdProject() throws Exception {
        ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Babylon");
        project.setDesc(projectDescription);
        when(projectService.getProjectById(1)).thenReturn(project);
        mvc.perform(get("/project/1/investments"))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "Investments in project: Babylon"))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }*//*

    @Test(timeout = 2000)
    public void getAllProjectInvestmentsShouldReturnPageWithAllInvestmentsFromIdProject() throws Exception {

        DtoProject dtoProject = getTestDtoProject();
        when(projectService.getProjectById(1)).thenReturn(project);
        mvc.perform(get("/project/1/investments"))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "Investments in project: Babylon"))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }


    @Test(timeout = 2000)
    public void getAllUserInvestmentsShouldReturnPageWithAllInvestmentsOfCurrentUser() throws Exception {
        when(principal.getName()).thenReturn(TEST_EMAIL);
        when(userService.getUserByEmail(TEST_EMAIL)).thenReturn(user);
        when(userService.getUserById(1)).thenReturn(dtoUser);
        mvc.perform(get("/user/investments").principal(principal))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "Investments of user: Ivan Mazepa"))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }

    @Test(timeout = 2000)
    public void getInvestmentById() throws Exception {
        mvc.perform(get("/api/investment/1"));
        verify(investmentService).getDtoInvestmentById(1L);
        verifyNoMoreInteractions(investmentService);
    }

    private Investment createInvestment(Project investmentProject, User user, BigDecimal sum) {
        return new Investment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                project, user, sum, false, false);
    }*/
}