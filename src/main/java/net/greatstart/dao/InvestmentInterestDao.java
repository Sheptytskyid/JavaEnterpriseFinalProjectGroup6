/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.InvestmentInterest}.
 */
package net.greatstart.dao;

import net.greatstart.model.InvestmentInterest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvestmentInterestDao extends PagingAndSortingRepository<InvestmentInterest, Long> {

}
