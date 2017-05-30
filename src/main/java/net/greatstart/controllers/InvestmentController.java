package net.greatstart.controllers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.mappers.CycleAvoidingMappingContext;
import net.greatstart.mappers.InvestmentMapper;
import net.greatstart.model.Investment;
import net.greatstart.model.Project;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentService;
import net.greatstart.services.ProjectService;
import net.greatstart.services.UserService;
import net.greatstart.validators.InvestmentValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
/*@RequestMapping("/api")*/
public class InvestmentController {
    private static final String PROJECT_PAGE = "project/";
    private static final String INVESTMENTS_VIEW = "investment/investments";
    private static final String REDIRECT = "redirect:/";
    private static final String INVESTMENT_LIST = "investmentList";
    private static final String PAGE_NAME = "pageName";

    private InvestmentService investmentService;
    private InvestmentMapper investmentMapper;
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

    @GetMapping("/api/investment/{id}")
    @ResponseBody
    public DtoInvestment getInvestmentById(@PathVariable long id) {

        return investmentService.getDtoInvestmentById(id);
    }

    @PutMapping("/api/investment/{id}")
    @ResponseBody
    public ResponseEntity<DtoInvestment> updateInvestment(@PathVariable long id,
                                                          @RequestBody DtoInvestment investment) {
        if (investmentService.getInvestmentById(id) != null) {
            investmentService.deleteInvestment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/investment/{id}")
    @ResponseBody
    public ResponseEntity<Investment> deleteInvestmentById(@PathVariable long id) {
        if (investmentService.getInvestmentById(id) != null) {
            investmentService.deleteInvestment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/investment")
    @ResponseBody
    public List<DtoInvestment> getInvestments() {
        return investmentService.getAllDtoInvestments();
    }

    @GetMapping("/api/project/{id}/investments")
    @ResponseBody
    public ResponseEntity<List<DtoInvestment>> getAllProjectInvestments(@PathVariable long id) {
        List<DtoInvestment> investments = new ArrayList<>();
        projectService.getProjectById(id).getInvestments()
                .forEach(investment -> investments.add(investmentMapper
                        .fromInvestmentToDto(investment, new CycleAvoidingMappingContext())));
        if (investments.isEmpty()) {
            return new ResponseEntity<>(investments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/investment")
    public ModelAndView getAllInvestments() {
        ModelAndView model = new ModelAndView(INVESTMENTS_VIEW);
        model.addObject(PAGE_NAME, "All investments.");
        model.addObject(INVESTMENT_LIST, investmentService.getAllInvestments());
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
        } else {
            investmentService.saveInvestment(createInvestment(project, investor, sum));
        }
        return model;
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
