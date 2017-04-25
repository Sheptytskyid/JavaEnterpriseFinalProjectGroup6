package net.greatstart.controller;

import net.greatstart.model.Investment;
import net.greatstart.services.InvestmentService;

import java.util.List;

public class InvestmentController {

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
