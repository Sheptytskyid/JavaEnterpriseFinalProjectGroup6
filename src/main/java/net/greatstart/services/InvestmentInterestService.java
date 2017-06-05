package net.greatstart.services;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.model.InvestmentInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Business logic layer for {@link net.greatstart.model.InvestmentInterest}.
 */

@Service
@Transactional
public class InvestmentInterestService {

    private InvestmentInterestDao investmentInterestDao;

    @Autowired
    public InvestmentInterestService(InvestmentInterestDao investmentInterestDao) {
        this.investmentInterestDao = investmentInterestDao;
    }

    public InvestmentInterest saveInvestmentInterest(InvestmentInterest investmentInterest) {
        return investmentInterestDao.save(investmentInterest);
    }

    public void deleteInvestmentInterest(long id) {
        InvestmentInterest investmentInterest = getInvestmentInterestById(id);
        investmentInterestDao.delete(investmentInterest);
    }

    public InvestmentInterest getInvestmentInterestById(long id) {
        return investmentInterestDao.findOne(id);
    }

    public List<InvestmentInterest> getAllInvestmentInterest() {
        List<InvestmentInterest> interests = new ArrayList<>();
        investmentInterestDao.findAll().forEach(interests::add);
        return interests;
    }
}
