package net.greatstart.controllers;

import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.validators.InvestmentValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class InvestmentController {
    private static final String PROJECT_PAGE = "project/";
    private static final String EDIT_PROFILE = "redirect:/user/EditUserPage";

    private InvestmentService investmentService;
    private ProjectService projectService;
    private UserService userService;

    @Autowired
    public InvestmentController(InvestmentService investmentService,
                                ProjectService projectService, UserService userService) {
        this.investmentService = investmentService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/project/{id}/addInvestment")
    public ModelAndView getAddInvestmentForm(@PathVariable long id) {
        Project project = projectService.getProjectById(id);

        ModelAndView modelAndView = new ModelAndView("investment/add_investment");
        modelAndView.addObject("investment", new Investment());
        modelAndView.addObject(project);
        return modelAndView;
    }

    @PostMapping("/project/{id}/addInvestment")
    public ModelAndView addInvestment(@PathVariable long id,
                                      @RequestParam BigDecimal sum,
                                      Principal principal) {
        User investor = userService.getUserByEmail(principal.getName());
        if (investor.getContact().getPhoneNumber() == null
                || investor.getContact().getPhoneNumber().isEmpty()) {
            ModelAndView modelAndView = new ModelAndView(EDIT_PROFILE);
            modelAndView.addObject("message", "Please enter your phone number!");
            return modelAndView;
        }

        Project project = projectService.getProjectById(id);
        String message = InvestmentValidationService.validate(sum, project);
        if (message != null) {
            ModelAndView modelAndView = new ModelAndView("/project/" + id + "/addInvestment");
            modelAndView.addObject("message", message);
            return modelAndView;
        }
        investmentService.saveInvestment(new Investment(LocalDateTime.now(),
                project, investor, sum, false, false));

        return new ModelAndView(PROJECT_PAGE + id);
    }

    public Investment getInvestmentById(long id) {
        return investmentService.getInvestmentById(id);
    }

    public List<Investment> getAllnvestments() {
        return investmentService.getAllInvestments();
    }
}
