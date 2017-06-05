/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Investment}.
 */
package net.greatstart.dao;

import net.greatstart.model.Investment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvestmentDao extends PagingAndSortingRepository<Investment, Long> {

}
