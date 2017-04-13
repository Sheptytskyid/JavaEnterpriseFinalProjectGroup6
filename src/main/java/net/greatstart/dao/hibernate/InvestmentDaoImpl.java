package net.greatstart.dao.hibernate;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;

import java.util.List;

public class InvestmentDaoImpl implements InvestmentDao {
    @Override
    public boolean create(Investment item) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(Investment item) {
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
