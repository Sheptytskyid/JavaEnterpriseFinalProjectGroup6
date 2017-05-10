package net.greatstart.dao;

import net.greatstart.model.Investment;

import java.util.List;

public interface InvestmentDao extends GenericDao<Investment> {
    List<Investment> getAllByUserId(long id);

    List<Investment> getAllByProjectId(long id);
}
