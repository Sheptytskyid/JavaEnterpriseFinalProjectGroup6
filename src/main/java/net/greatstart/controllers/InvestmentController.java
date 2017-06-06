package net.greatstart.controllers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import net.greatstart.services.InvestmentService;
import net.greatstart.validators.InvestmentValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * A REST controller to manage {@link net.greatstart.model.Investment} requests.
 */

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    private InvestmentService investmentService;
    private InvestmentValidationService investmentValidationService;

    @Autowired
    public InvestmentController(InvestmentService investmentService,
                                InvestmentValidationService investmentValidationService) {
        this.investmentService = investmentService;
        this.investmentValidationService = investmentValidationService;
    }

    @GetMapping
    public ResponseEntity<List<DtoInvestment>> getAllInvestments() {
        List<DtoInvestment> investments = investmentService.getAllDtoInvestments();
        return new ResponseEntity<>(investments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DtoInvestment> getInvestmentById(@PathVariable long id) {
        DtoInvestment dtoInvestment = investmentService.getDtoInvestmentById(id);
        if (dtoInvestment != null) {
            return new ResponseEntity<>(dtoInvestment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("my")
    public ResponseEntity<List<DtoInvestment>> getUserInvestments(Principal principal) {
        List<DtoInvestment> dtoInvestments = investmentService.getUserDtoInvestmentsByUserEmail(principal.getName());
        return new ResponseEntity<>(dtoInvestments, HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<DtoInvestment>> getProjectInvestments(@PathVariable long id) {
        List<DtoInvestment> investments = investmentService.getDtoProjectInvestments(id);
        return new ResponseEntity<>(investments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DtoInvestment> createInvestment(@Valid @RequestBody DtoInvestment investment) {
        if (investmentValidationService.validate(investment)) {
            DtoInvestment investmentResult = investmentService.saveInvestment(investment);
            if (investmentResult != null && investmentResult.getId() != 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DtoInvestment> updateInvestment(@PathVariable long id,
                                                          @RequestBody DtoInvestment investment) {
        if (investmentService.updateInvestment(investment) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Investment> deleteInvestmentById(@PathVariable long id) {
        if (investmentService.getDtoInvestmentById(id) != null) {
            investmentService.deleteInvestment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
