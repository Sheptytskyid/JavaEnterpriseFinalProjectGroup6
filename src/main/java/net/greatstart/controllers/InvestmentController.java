package net.greatstart.controllers;

import net.greatstart.dto.DtoInvestment;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
/*@RequestMapping("/api")*/
public class InvestmentController {
    private static final String PROJECT_PAGE = "project/";
    private static final String REDIRECT = "redirect:/";

    private InvestmentService investmentService;
    private InvestmentMapper investmentMapper;
    private ProjectService projectService;
    private UserService userService;
    private InvestmentValidationService investmentValidationService;

    @Autowired
    public InvestmentController(InvestmentService investmentService,
                                InvestmentMapper investmentMapper, ProjectService projectService, UserService userService, InvestmentValidationService
                                        investmentValidationService) {
        this.investmentService = investmentService;
        this.investmentMapper = investmentMapper;
        this.projectService = projectService;
        this.userService = userService;
        this.investmentValidationService = investmentValidationService;
    }

    @GetMapping("/api/investment/{id}")
    @ResponseBody
    public DtoInvestment getInvestmentById(@PathVariable long id) {

        return investmentService.getDtoInvestmentById(id);
    }

    @PostMapping("/api/investment")
    @ResponseBody
    public ResponseEntity<DtoInvestment> createInvestment(@Valid @RequestBody DtoInvestment investment) {
        //todo create investment
        System.out.println(investment);
        Investment currentInvestment = investmentMapper.investmentFromDto(investment);
        System.out.println(currentInvestment);
        currentInvestment.setDateOfInvestment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        currentInvestment.setVerified(false);
        currentInvestment.setVerified(false);
        System.out.println(currentInvestment);
        Long id = investment.getProject().getId();
        Project currentProject = projectService.getProjectById(id);
        currentInvestment.setProject(currentProject);
        System.out.println(currentInvestment);
        currentInvestment.setInvestor(userService.getUser(investment.getInvestor().getId()));
        System.out.println(currentInvestment);
        investmentService.saveInvestment(currentInvestment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/investment/{id}")
    @ResponseBody
    public ResponseEntity<DtoInvestment> updateInvestment(@PathVariable long id,
                                                          @RequestBody DtoInvestment investment) {
        if (investmentService.getInvestmentById(id) != null) {
            //todo update investment
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

    @PreAuthorize("isAuthenticated()")

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
