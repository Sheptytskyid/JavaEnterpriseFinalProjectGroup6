package net.greatstart.controllers;

import net.greatstart.model.InvestmentInterest;
import net.greatstart.services.InvestmentInterestService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentInterestControllerTest {

    public static final String REDIRECT_INVINTEREST = "redirect:/invinterest/";
    public static final long ID = 1;
    @Mock
    private InvestmentInterestService service;
    @Mock
    private UserService userService;
    @InjectMocks
    private InvestmentInterestController controller;
    private MockMvc mvc;
    private final String USERNAME = "";
    private Principal principal = () -> USERNAME;

    @Before
    public void setup() {
        mvc = standaloneSetup(controller).build();
    }

    @Test(timeout = 2000)
    public void showInvestmentsInterestShouldInvokeServiceGetAllInvestmentInterestsAndReturnView() throws Exception {
        mvc.perform(get("/invinterest")).andExpect(view().name("invinterest/invinterests"));
        verify(service, times(1)).getAllInvestmentInterest();
    }

    @Test(timeout = 2000)
    public void getInvestmentInterestForm() throws Exception {
        mvc.perform(get("/invinterest/add")).andExpect(view().name("invinterest/add_invinterest"));

    }

    @Test(timeout = 2000)
    public void addInvestmentInterestShouldReturnViev() throws Exception {
        InvestmentInterest interest = new InvestmentInterest();
        mvc.perform(post("/invinterest/add")
            .principal(principal))
            .andExpect(view().name(REDIRECT_INVINTEREST));
        verify(userService, times(1)).getUserByEmail(principal.getName());
        verify(service, times(1)).saveInvestmentInterest(interest);
    }

    @Test(timeout = 2000)
    public void deleteInvestmentInterest() throws Exception {
        mvc.perform(get("/invinterest/" + ID + "/delete")).andExpect(view().name(REDIRECT_INVINTEREST));
        verify(service, times(1)).deleteInvestmentInterest(ID);
    }

    @Test(timeout = 2000)
    public void getUpdateFormInvestmentInterestExistingId() throws Exception {
        mvc.perform(get("/invinterest/" + ID + "/update")).andExpect(view().name("invinterest/update_invinterest"));
        verify(service, times(1)).getInvestmentInterestById(ID);
    }

    @Test(timeout = 2000)
    public void getUpdateFormInvestmentInterest() throws Exception {
        mvc.perform(get("/invinterest/0/update")).andExpect(view().name("invinterest/invinterests"));
    }

    @Test(timeout = 2000)
    public void updateInvestmentInterest() throws Exception {
        InvestmentInterest interest = new InvestmentInterest();
        mvc.perform(post("/invinterest/1/update")).andExpect(view().name(REDIRECT_INVINTEREST));
        verify(service, times(1)).saveInvestmentInterest(interest);
    }
}
