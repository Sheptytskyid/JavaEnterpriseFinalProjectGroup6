package net.greatstart.controllers;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.validators.InvestmentValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
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

    @GetMapping("/project/{projectId}/invest")
    public ModelAndView getAddInvestmentForm(@PathVariable long projectId) {
        ModelAndView model = new ModelAndView("investment");
        Investment investment = new Investment();
        investment.setProject(projectService.getProjectById(projectId));
        model.addObject("investment", investment);
        return model;
    }

    @PostMapping("/project/{projectId}/invest")
    public ModelAndView addInvestment(@PathVariable long projectId,
                                      @RequestParam BigDecimal sum,
                                      Errors errors,
                                      Principal principal) {
        User investor = userService.getUserByEmail(principal.getName());
        Project currentProject = projectService.getProjectById(projectId);
        InvestmentValidationService.validate(errors, sum, investor, currentProject);
        Investment investment = new Investment();
        investment.setSum(sum);
        investment.setProject(currentProject);
        investment.setInvestor(investor);
        investment.setVerified(false);
        investment.setPaid(false);
        investmentService.createInvestment(investment);

        return new ModelAndView(SHOW_PROFILE);
    }

    @RequestMapping("investment/{id}")
    public Investment getInvestmentById(@PathVariable long id) {

        return investmentService.getInvestmentById(id);
    }


}
