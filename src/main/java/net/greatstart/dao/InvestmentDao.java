package net.greatstart.dao;

import net.greatstart.model.Investment;
import org.springframework.data.repository.CrudRepository;

public interface InvestmentDao extends CrudRepository<Investment, Long> {

}
