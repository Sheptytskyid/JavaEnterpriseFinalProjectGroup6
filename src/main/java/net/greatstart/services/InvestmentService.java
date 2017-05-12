package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvestmentService {

    private InvestmentDao investmentDao;

    @Autowired
    public InvestmentService(InvestmentDao investmentDao) {
        this.investmentDao = investmentDao;
    }

    public Investment saveInvestment(Investment investment) {
        return investmentDao.save(investment);
    }

    public Investment getInvestmentById(long id) {
        return investmentDao.findOne(id);
    }

    public List<Investment> getAllInvestments() {
        List<Investment> investments = new ArrayList<>();
        investmentDao.findAll().forEach(investments::add);
        return investments;
    }
}
