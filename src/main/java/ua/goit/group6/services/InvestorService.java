package ua.goit.group6.services;

import ua.goit.group6.dao.InvestorDao;
import ua.goit.group6.model.Investor;
import java.util.List;

public class InvestorService {

    private InvestorDao investorDao;

    public InvestorService(InvestorDao investorDao) {
        this.investorDao = investorDao;
    }

    public void createInvestor(Investor investor) {
        investorDao.create(investor);
    }

    public Investor getInvestor(String email) {
        return investorDao.getByEmail(email);
    }

    public boolean updateInvestor(Investor investor) {
        return investorDao.update(investor);
    }

    public List<Investor> getAllInvestors() {
        return investorDao.getAll();
    }

}
