package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentDao investmentDao;

    public boolean createInvestment(Investment investment) {
        return investmentDao.create(investment);
    }

    public boolean deleteInvestment(Investment investment) {
        return investmentDao.delete(investment);
    }

    public Investment getnvestmentById(long id) {
        return investmentDao.getById(id);
    }

    public List<Investment> getAllInvestments() {
        return investmentDao.getAll();
    }
}
