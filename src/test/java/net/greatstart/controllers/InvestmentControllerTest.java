package net.greatstart.controllers;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentControllerTest {

    @Mock
    private InvestmentService investmentService;
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @InjectMocks
    private InvestmentController investmentController;
    private Project project;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        project = new Project();
        mvc = standaloneSetup(investmentController).build();
    }

    @Test
    public void getAddInvestmentForm() throws Exception {
        Investment investment = new Investment();
        BigDecimal bigDecimal = new BigDecimal(123);
        List<Investment> investments = new ArrayList<>();
        investments.add(investment);
        investment.setSum(bigDecimal);
        project.setInvestments(investments);
        when(projectService.getProjectById(1)).thenReturn(project);
        mvc.perform(get("/project/1/investment/add"))
            .andExpect(view().name("investment/add_investment"))
            .andExpect(model().attribute("project", project))
            .andExpect(model().attribute("investedSum", bigDecimal));
    }

    @Test
    public void addInvestment() throws Exception {

    }

    @Test
    public void getInvestmentById() throws Exception {
    }

    @Test
    public void getAllInvestments() throws Exception {
        List<Investment> investments = new ArrayList<>();
        when(investmentService.getAllInvestments()).thenReturn(investments);
        mvc.perform(get("/investment")).andExpect(view().name("investment/investments"))
        .andExpect(model().attribute("pageName", "All investments."))
        .andExpect(model().attribute("investmentList", investments));
    }

    @Test
    public void getAllProjectInvestments() throws Exception {
    }

    @Test
    public void getAllUserInvestments() throws Exception {
    }

    @Test
    public void deleteInvestmentById() throws Exception {
    }

}