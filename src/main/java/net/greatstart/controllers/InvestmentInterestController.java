package net.greatstart.controllers;

import net.greatstart.model.InvestmentInterest;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentInterestService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class InvestmentInterestController {
    private static final String REDIRECT_TO_INVESTMENT_INTEREST = "redirect:/investmentinterest/";

    private InvestmentInterestService investmentInterestService;
    private UserService userService;

    @Autowired
    public InvestmentInterestController(InvestmentInterestService investmentInterestService, UserService userService) {
        this.investmentInterestService = investmentInterestService;
        this.userService = userService;
    }

    @RequestMapping(value = "/investmentinterest/")
    public ModelAndView showInvestmentsInterest(){
        List<InvestmentInterest> investmentInterestList = this.investmentInterestService
                .getAllInvestmentInterest();
        ModelAndView model = new ModelAndView("investmentinterest/investmentsinterests");
        model.addObject(investmentInterestList);
        return model;
    }

    @RequestMapping(value = "/investmentinterest/add", method = RequestMethod.GET)
    public ModelAndView getInvestmentInterestForm(){
        ModelAndView model = new ModelAndView("investmentinterest/add_investmentinterest");
        model.addObject("investmentinterest", new InvestmentInterest());
        return model;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/investmentinterest/add", method = RequestMethod.POST)
    public ModelAndView addInvestmentInterest(@Valid InvestmentInterest investmentInterest,
                                              Errors errors,
                                              Principal principal) {
        if (errors.hasErrors()) {
            return new ModelAndView("investmentinterest/add_investmentinterest");
        }
        User investor = userService.getByUsername("");
        investmentInterest.setUser(investor);
        this.investmentInterestService.createInvestmentInterest(investmentInterest);
        return new ModelAndView(REDIRECT_TO_INVESTMENT_INTEREST);
    }

    @RequestMapping(value = "investmentinterest/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteInvestmentInterest(@PathVariable("id") Long id) {
        this.investmentInterestService.deleteInvestmentInterest(id);
        return new ModelAndView(REDIRECT_TO_INVESTMENT_INTEREST);
    }

    @RequestMapping(value = "investmentinterest/{id}/update", method = RequestMethod.GET)
    public ModelAndView getUpdateFormInvestmentInterest(@PathVariable("id") Long id) {
        if (id > 0) {
            ModelAndView model = new ModelAndView("investmentinterest/update_investmentinterest");
            InvestmentInterest investmentInterest = this.investmentInterestService.getInvestmentInterestById(id);
            model.addObject("investmentInterest", investmentInterest);
            return model;
        }
        return new ModelAndView("investmentinterest/investmentsinterests");
    }

    @RequestMapping(value = "investmentinterest/{id}/update", method = RequestMethod.POST)
    public ModelAndView updateInvestmentInterest(@PathVariable("id") Long id, @Valid InvestmentInterest investmentInterest,
                                                 Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("investmentinterest/update_investmentinterest");
        }
        this.investmentInterestService.updateInvestmentInterest(investmentInterest);
        return new ModelAndView(REDIRECT_TO_INVESTMENT_INTEREST);
    }
}
