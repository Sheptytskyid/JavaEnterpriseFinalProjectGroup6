package net.greatstart.controllers;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentControllerTest {
    private static final String PAGE_NAME = "pageName";
    private static final String INVESTMENT_LIST = "investmentList";
    private static final String INVESTMENTS_VIEW = "investment/investments";
    private static final String TEST_EMAIL = "ert@ert.ert";

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
    private Project project;
    private List<Investment> investments;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(investmentController).build();
        project = new Project();
        Investment investment1 = new Investment();
        investment1.setSum(new BigDecimal(123));
        Investment investment2 = new Investment();
        investment2.setSum(new BigDecimal(456));
        investments = Arrays.asList(investment1, investment2);
        project.setInvestments(investments);
    }

    @Test(timeout = 2000)
    public void getAddInvestmentForm() throws Exception {
        when(projectService.getProjectById(1)).thenReturn(project);
        mvc.perform(get("/project/1/investment/add"))
                .andExpect(view().name("investment/add_investment"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("investedSum", new BigDecimal(579)));
    }

    @Test(timeout = 2000)
    public void addInvestment() throws Exception {
    }

    @Test(timeout = 2000)
    public void getAllInvestments() throws Exception {
        when(investmentService.getAllInvestments()).thenReturn(investments);
        mvc.perform(get("/investment"))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "All investments."))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }

    @Test(timeout = 2000)
    public void getAllProjectInvestments() throws Exception {
        ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Babylon");
        project.setDesc(projectDescription);
        when(projectService.getProjectById(1)).thenReturn(project);
        mvc.perform(get("/project/1/investments"))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "Investments in project: Babylon"))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }

    @Test(timeout = 2000)
    public void getAllUserInvestments() throws Exception {
        User user = new User();
        user.setName("Ivan");
        user.setLastName("Mazepa");
        user.setEmail(TEST_EMAIL);
        when(principal.getName()).thenReturn(TEST_EMAIL);
        when(userService.getUserByEmail(TEST_EMAIL)).thenReturn(user);
        user.setInvestments(investments);
        when(userService.getUserById(1)).thenReturn(user);
        mvc.perform(get("/user/investments").principal(principal))
                .andExpect(view().name(INVESTMENTS_VIEW))
                .andExpect(model().attribute(PAGE_NAME, "Investments of user: Ivan Mazepa"))
                .andExpect(model().attribute(INVESTMENT_LIST, investments));
    }

    @Test(timeout = 2000)
    public void deleteInvestmentById() throws Exception {
        mvc.perform(get("/investment/1/delete"))
                .andExpect(view().name("redirect:/investment"));
        verify(investmentService, times(1)).deleteInvestment(1L);
    }

}