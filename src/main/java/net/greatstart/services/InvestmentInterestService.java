package net.greatstart.services;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.model.InvestmentInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvestmentInterestService {

    private InvestmentInterestDao investmentInterestDao;

    @Autowired
    public InvestmentInterestService(InvestmentInterestDao investmentInterestDao) {
        this.investmentInterestDao = investmentInterestDao;
    }

    public boolean createInvestmentInterest(InvestmentInterest investmentInterest) {
        return this.investmentInterestDao.create(investmentInterest);
    }

    public boolean updateInvestmentInterest(InvestmentInterest investmentInterest) {
        return this.investmentInterestDao.update(investmentInterest);
    }

    public boolean deleteInvestmentInterest(long id) {
        InvestmentInterest investmentInterest = getInvestmentInterestById(id);
        return this.investmentInterestDao.delete(investmentInterest);
    }

    public InvestmentInterest getInvestmentInterestById(long id) {
        return this.investmentInterestDao.getById(id);
    }

    public List<InvestmentInterest> getAllInvestmentInterest() {
        return this.investmentInterestDao.getAll();
    }
}
