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
    private static final String INVESTMENTS_VIEW = "investment/investments";
    private static final String INVESTMENTS_PAGE = "investment";
    private static final String EDIT_USER_PROFILE = "user/EditUserPage";
    private static final String REDIRECT = "redirect:/";

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
        ModelAndView model = new ModelAndView("investment/add_investment");
        model.addObject(project);
        model.addObject("investedSum", getInvestedSumInProject(id));
        return model;
    }

    @PostMapping("/project/{id}/addInvestment")
    public ModelAndView addInvestment(@PathVariable long id,
                                      @RequestParam BigDecimal sum,
                                      Principal principal) {
        Project project = projectService.getProjectById(id);
        User investor = userService.getUserByEmail(principal.getName());
        String message = InvestmentValidationService.validate(sum, project);
        if (investor.getContact().getPhoneNumber() == null
                || investor.getContact().getPhoneNumber().isEmpty()) {
            ModelAndView model = new ModelAndView(REDIRECT + EDIT_USER_PROFILE);
            model.addObject("message", "Please enter your phone number!");
            return model;
        }
        if (message != null) {
            ModelAndView model = new ModelAndView("/project/" + id + "/addInvestment");
            model.addObject("message", message);
            model.addObject("investedSum", getInvestedSumInProject(id));
            return model;
        }
        investmentService.saveInvestment(new Investment(LocalDateTime.now(),
                project, investor, sum, false, false));

        return new ModelAndView(REDIRECT + PROJECT_PAGE + id);
    }

    @GetMapping("investment/{id}")
    public Investment getInvestmentById(@PathVariable long id) {
        return investmentService.getInvestmentById(id);
    }

    @GetMapping("investment")
    public ModelAndView getAllInvestments() {
        ModelAndView model = new ModelAndView(INVESTMENTS_VIEW);
        model.addObject("investmentList",
                investmentService.getAllInvestments());
        return model;
    }

    @GetMapping("project/{id}/investments")
    public List<Investment> getAllProjectInvestments(@PathVariable long id) {
        return projectService.getProjectById(id).getInvestments();
    }

    @GetMapping("user/{id}/investments")
    public List<Investment> getAllUserInvestments(Principal principal) {
        return userService.getUserByEmail(principal.getName()).getInvestments();
    }

    //todo

    @GetMapping("investment/{id}/delete")
    public ModelAndView deleteInvestmentById(@PathVariable long id) {
        investmentService.deleteInvestment(id);
        return new ModelAndView(REDIRECT + INVESTMENTS_PAGE);
    }

    private BigDecimal getInvestedSumInProject(long id) {
        return projectService.getProjectById(id).getInvestments()
                .stream().map(Investment::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
