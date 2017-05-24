package net.greatstart.controllers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.validators.InvestmentValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class InvestmentController {
    private static final String PROJECT_PAGE = "project/";
    private static final String INVESTMENTS_VIEW = "investment/investments";
    private static final String INVESTMENTS_PAGE = "investment";
    private static final String REDIRECT = "redirect:/";
    private static final String INVESTMENT_LIST = "investmentList";
    private static final String PAGE_NAME = "pageName";

    private InvestmentService investmentService;
    private ProjectService projectService;
    private UserService userService;
    private InvestmentValidationService investmentValidationService;

    @Autowired
    public InvestmentController(InvestmentService investmentService,
                                ProjectService projectService, UserService userService, InvestmentValidationService
                                        investmentValidationService) {
        this.investmentService = investmentService;
        this.projectService = projectService;
        this.userService = userService;
        this.investmentValidationService = investmentValidationService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/project/{id}/investment/add")
    public ModelAndView getAddInvestmentForm(@PathVariable long id) {
        Project project = projectService.getProjectById(id);
        ModelAndView model = new ModelAndView("investment/add_investment");
        model.addObject("project", project);
        model.addObject("investedSum", getInvestedSumInProject(id));
        return model;
    }

    @PostMapping("/project/{id}/investment/add")
    public ModelAndView addInvestment(@PathVariable long id,
                                      @RequestParam BigDecimal sum,
                                      Principal principal) {
        ModelAndView model = new ModelAndView();
        Project project = projectService.getProjectById(id);
        User investor = userService.getUserByEmail(principal.getName());
        String message = investmentValidationService.validate(sum, project);
        model.setViewName(REDIRECT + PROJECT_PAGE + id);
        if (message != null) {
            model.setViewName("investment/add_investment");
            model.addObject("message", message);
            model.addObject(project);
            model.addObject("investedSum", getInvestedSumInProject(id));
        }
        investmentService.saveInvestment(createInvestment(project, investor, sum));

        return model;
    }

    @GetMapping("/investment/{id}")
    @ResponseBody
    public DtoInvestment getInvestmentById(@PathVariable long id) {
        return investmentService.getDtoInvestmentById(id);
    }

    @GetMapping("/investment")
    public ModelAndView getAllInvestments() {
        ModelAndView model = new ModelAndView(INVESTMENTS_VIEW);
        model.addObject(PAGE_NAME, "All investments.");
        model.addObject(INVESTMENT_LIST, investmentService.getAllInvestments());
        return model;
    }

    @GetMapping("/investment/json")
    @ResponseBody
    public List<DtoInvestment> getInvestments() {
        return investmentService.getAllDtoInvestments();
    }

    @GetMapping("/project/{id}/investments")
    public ModelAndView getAllProjectInvestments(@PathVariable long id) {
        ModelAndView model = new ModelAndView(INVESTMENTS_VIEW);

        model.addObject(PAGE_NAME,
                "Investments in project: "
                        + projectService.getProjectById(id).getDesc().getName());
        model.addObject(INVESTMENT_LIST,
                projectService.getProjectById(id).getInvestments());
        return model;
    }

    @GetMapping("/user/investments")
    public ModelAndView getAllUserInvestments(Principal principal) {
        ModelAndView model = new ModelAndView(INVESTMENTS_VIEW);
        model.addObject(PAGE_NAME, String.format("Investments of user: %s %s",
                userService.getUserByEmail(principal.getName()).getName(),
                userService.getUserByEmail(principal.getName()).getLastName()));
        model.addObject(INVESTMENT_LIST,
                userService.getUserByEmail(principal.getName()).getInvestments());
        return model;
    }


    @GetMapping("/investment/{id}/delete")
    public ModelAndView deleteInvestmentById(@PathVariable long id) {
        investmentService.deleteInvestment(id);
        return new ModelAndView(REDIRECT + INVESTMENTS_PAGE);
    }

    private BigDecimal getInvestedSumInProject(long id) {
        return projectService.getProjectById(id).getInvestments()
                .stream().map(Investment::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Investment createInvestment(Project project, User user, BigDecimal sum) {
        return new Investment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                project, user, sum, false, false);
    }

}
