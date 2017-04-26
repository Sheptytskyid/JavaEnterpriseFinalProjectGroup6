package net.greatstart.dao.hibernate;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class InvestmentDaoImpl implements InvestmentDao {
    @Override
    public boolean create(Investment investment) {
        return false;
    }

    @Override
    public boolean delete(Investment investment) {
        return false;
    }

    @Override
    public boolean update(Investment investment) {
        return false;
    }

    @Override
    public Investment getById(long id) {
        return null;
    }

    @Override
    public List<Investment> getAll() {
        return null;
    }
}
