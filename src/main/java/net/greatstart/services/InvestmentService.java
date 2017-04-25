package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;

import java.util.List;

public class InvestmentService {

    private InvestmentDao investmentDao;

    public boolean createInvestment(Investment investment) {
        return investmentDao.create(investment);
    }

    public Investment getnvestmentById(long id) {
        return investmentDao.getById(id);
    }

    public List<Investment> getAllInvestments() {
        return investmentDao.getAll();
    }
}
