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
    private static final String REDIRECT_TO_INVESTMENT_INTEREST = "redirect:/invinterest/";

    private InvestmentInterestService investmentInterestService;
    private UserService userService;

    @Autowired
    public InvestmentInterestController(InvestmentInterestService investmentInterestService, UserService userService) {
        this.investmentInterestService = investmentInterestService;
        this.userService = userService;
    }

    @RequestMapping("/invinterest")
    public ModelAndView showInvestmentsInterest() {
        List<InvestmentInterest> investmentInterestList = this.investmentInterestService
                .getAllInvestmentInterest();
        ModelAndView model = new ModelAndView("invinterest/invinterests");
        model.addObject("inv_interest_list", investmentInterestList);
        return model;
    }

    @RequestMapping(value = "/invinterest/add", method = RequestMethod.GET)
    public ModelAndView getInvestmentInterestForm() {
        ModelAndView model = new ModelAndView("invinterest/add_invinterest");
        model.addObject("invinterest", new InvestmentInterest());
        return model;
    }

    @RequestMapping(value = "/invinterest/add", method = RequestMethod.POST)
    public ModelAndView addInvestmentInterest(InvestmentInterest investmentInterest,
                                              Errors errors,
                                              Principal principal) {
        if (errors.hasErrors()) {
            return new ModelAndView("invinterest/add_invinterest");
        }
        User investor = userService.getByUsername("");
        investmentInterest.setInvestor(investor);
        this.investmentInterestService.createInvestmentInterest(investmentInterest);
        return new ModelAndView(REDIRECT_TO_INVESTMENT_INTEREST);
    }

    @RequestMapping(value = "/invinterest/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteInvestmentInterest(@PathVariable("id") Long id) {
        this.investmentInterestService.deleteInvestmentInterest(id);
        return new ModelAndView(REDIRECT_TO_INVESTMENT_INTEREST);
    }

    @RequestMapping(value = "/invinterest/{id}/update", method = RequestMethod.GET)
    public ModelAndView getUpdateFormInvestmentInterest(@PathVariable("id") Long id) {
        if (id > 0) {
            ModelAndView model = new ModelAndView("invinterest/update_invinterest");
            InvestmentInterest investmentInterest = this.investmentInterestService.getInvestmentInterestById(id);
            model.addObject("invinterest", investmentInterest);
            return model;
        }
        return new ModelAndView("invinterest/invinterests");
    }

    @RequestMapping(value = "/invinterest/{id}/update", method = RequestMethod.POST)
    public ModelAndView updateInvestmentInterest(@PathVariable("id") Long id, @Valid InvestmentInterest investmentInterest,
                                                 Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("investinterest/update_invinterest");
        }
        this.investmentInterestService.updateInvestmentInterest(investmentInterest);
        return new ModelAndView(REDIRECT_TO_INVESTMENT_INTEREST);
    }
}
