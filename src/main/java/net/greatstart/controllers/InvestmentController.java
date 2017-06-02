package net.greatstart.controllers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import net.greatstart.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    private InvestmentService investmentService;

    @Autowired
    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping
    public List<DtoInvestment> getInvestments() {
        return investmentService.getAllDtoInvestments();
    }

    @GetMapping("{id}")
    public DtoInvestment getInvestmentById(@PathVariable long id) {

        return investmentService.getDtoInvestmentById(id);
    }

    @PostMapping
    public ResponseEntity<DtoInvestment> createInvestment(@Valid @RequestBody DtoInvestment investment) {
        investment.setDateOfInvestment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        investment.setVerified(false);
        investment.setVerified(false);
        DtoInvestment investmentResult = investmentService.saveInvestment(investment);
        if (investmentResult != null && investmentResult.getId() != 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DtoInvestment> updateInvestment(@PathVariable long id,
                                                          @RequestBody DtoInvestment investment) {
//        if (investmentService.getInvestmentById(id) != null) {
//            //todo update investment
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
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
