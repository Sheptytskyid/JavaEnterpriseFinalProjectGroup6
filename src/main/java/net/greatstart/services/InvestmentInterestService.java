package net.greatstart.services;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.dto.DtoInterest;
import net.greatstart.mappers.InterestMapper;
import net.greatstart.model.Category;
import net.greatstart.model.InvestmentInterest;
import net.greatstart.model.User;
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
    private UserService userService;
    private InterestMapper interestMapper;
    private CategoryService categoryService;

    @Autowired
    public InvestmentInterestService(InvestmentInterestDao investmentInterestDao,
                                     UserService userService,
                                     InterestMapper interestMapper,
                                     CategoryService categoryService) {
        this.investmentInterestDao = investmentInterestDao;
        this.userService = userService;
        this.interestMapper = interestMapper;
        this.categoryService = categoryService;
    }

    public InvestmentInterest saveInterest(InvestmentInterest investmentInterest) {
        return investmentInterestDao.save(investmentInterest);
    }

    public List<DtoInterest> getUserDtoInterestsByUserEmail(String userEmail) {
        return getDtoInterestsFromInterests(userService.getUserByEmail(userEmail).getInvestmentInterests());
    }

    public DtoInterest updateDtoInterest(DtoInterest dtoInterest) {
        InvestmentInterest investmentInterest = interestMapper.interestFromDto(dtoInterest);
        investmentInterest = investmentInterestDao.save(investmentInterest);
        return interestMapper.fromInterestToDto(investmentInterest);
    }

    public DtoInterest createNewInterestFromDto(DtoInterest dtoInterest) {
        User investor = userService.getLoggedInUser();
        Category category = categoryService.findCategoryByName(dtoInterest.getCategory().getName());
        InvestmentInterest interest = interestMapper.interestFromDto(dtoInterest);
        interest.setInvestor(investor);
        interest.setCategory(category);
        interest = saveInterest(interest);
        return interestMapper.fromInterestToDto(interest);
    }

    public DtoInterest getDtoInterestById(long id) {
        return interestMapper.fromInterestToDto(investmentInterestDao.findOne(id));
    }

    public List<DtoInterest> getDtoInterestsFromInterests(List<InvestmentInterest> interests) {
        List<DtoInterest> dtoInterests = new ArrayList<>();
        interests.forEach(interest -> dtoInterests.add(interestMapper.fromInterestToDto(interest)));
        return dtoInterests;
    }

    public List<DtoInterest> getAllDtoInterest() {
        return getDtoInterestsFromInterests((List<InvestmentInterest>) investmentInterestDao.findAll());
    }

    public void deleteInvestmentInterest(long id) {
        InvestmentInterest investmentInterest = getInvestmentInterestById(id);
        investmentInterestDao.delete(investmentInterest);
    }

    public InvestmentInterest getInvestmentInterestById(long id) {
        return investmentInterestDao.findOne(id);
    }
}
