package net.greatstart.dao;

import net.greatstart.model.InvestmentInterest;
import net.greatstart.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.InvestmentInterest}.
 */

public interface InvestmentInterestDao extends PagingAndSortingRepository<InvestmentInterest, Long> {

    List<InvestmentInterest> findByInvestor(User investor);
}
