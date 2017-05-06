package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvestmentService {

    private InvestmentDao investmentDao;

    @Autowired
    public InvestmentService(InvestmentDao investmentDao) {
        this.investmentDao = investmentDao;
    }

    public boolean createInvestment(Investment investment) {
        return investmentDao.create(investment);
    }

    public Investment getInvestmentById(long id) {
        return investmentDao.getById(id);
    }

    public List<Investment> getAllInvestments() {
        return investmentDao.getAll();
    }
}
