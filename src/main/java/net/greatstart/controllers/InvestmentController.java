/**
 * A REST controller to manage {@link net.greatstart.model.Investment} requests.
 */
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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

    @Transactional
    @GetMapping
    public ResponseEntity<List<DtoInvestment>> getInvestments() {
        if (!investmentService.getAllDtoInvestments().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<DtoInvestment> getInvestmentById(@PathVariable long id) {
        if (investmentService.getDtoInvestmentById(id) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PostMapping({"", "/"})
    public ResponseEntity<DtoInvestment> createInvestment(@Valid @RequestBody DtoInvestment investment) {
        if (investmentValidationService.validate(investment)) {
            DtoInvestment investmentResult = investmentService.saveInvestment(investment);
            if (investmentResult != null && investmentResult.getId() != 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<DtoInvestment> updateInvestment(@PathVariable long id,
                                                          @RequestBody DtoInvestment investment) {
        //todo: implement update investment
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Investment> deleteInvestmentById(@PathVariable long id) {
        if (investmentService.getDtoInvestmentById(id) != null) {
            investmentService.deleteInvestment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
