package net.greatstart.controllers;

import net.greatstart.model.Investment;
import net.greatstart.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    public boolean createInvestment(Investment investment) {
        return investmentService.createInvestment(investment);
    }

    public Investment getInvestmentById(long id) {
        return investmentService.getnvestmentById(id);
    }

    public List<Investment> getAllnvestments() {
        return investmentService.getAllInvestments();
    }
}
