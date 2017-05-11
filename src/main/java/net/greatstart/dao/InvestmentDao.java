package net.greatstart.dao;

import net.greatstart.model.Investment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvestmentDao extends PagingAndSortingRepository<Investment, Long> {

}
