package net.greatstart.controllers;

import net.greatstart.model.Investment;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/investment")
public class InvestmentController {
    private static final String SHOW_PROFILE = "user/showProfile";

    private InvestmentService investmentService;
    private ProjectService projectService;
    private UserService userService;

    public InvestmentController(InvestmentService investmentService, ProjectService projectService, UserService userService) {
        this.investmentService = investmentService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public ModelAndView getAddInvestmentForm(@RequestParam long projectId) {
        ModelAndView model = new ModelAndView("investment");
        Investment investment = new Investment();
        investment.setProject(projectService.getProjectById(projectId));
        model.addObject("investment", investment);
        return model;
    }

    @PostMapping("/new")
    public ModelAndView addInvestment(Investment investment,
                                         Errors errors,
                                         Principal principal) {
        User investor = userService.getUserByEmail(principal.getName());
        if (investor.getContact().getPhoneNumber().isEmpty()
                || investor.getContact().getPhoneNumber() == null) {
            ModelAndView modelAndView = new ModelAndView(SHOW_PROFILE);
            //todo
            return modelAndView;
        }
        if (errors.hasErrors()) {
            return new ModelAndView("/investment/new");
        }
//        investment.
        return new ModelAndView();
    }


    public Investment getInvestmentById(long id) {
        return investmentService.getInvestmentById(id);
    }

    public List<Investment> getAllnvestments() {
        return investmentService.getAllInvestments();
    }
}
