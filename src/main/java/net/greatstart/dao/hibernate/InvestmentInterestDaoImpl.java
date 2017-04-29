package net.greatstart.dao.hibernate;

import net.greatstart.dao.InvestmentInterestDao;
import net.greatstart.model.InvestmentInterest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvestmentInterestDaoImpl implements InvestmentInterestDao {
    private SessionFactory sessionFactory;

    @Autowired
    public InvestmentInterestDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean create(InvestmentInterest item) {
        sessionFactory.getCurrentSession()
                .save(item);
        return true;
    }

    @Override
    public boolean update(InvestmentInterest item) {
        sessionFactory.getCurrentSession()
                .update(item);
        return true;
    }

    @Override
    public boolean delete(InvestmentInterest item) {
        sessionFactory.getCurrentSession()
                .delete(item);
        return true;
    }

    @Override
    public InvestmentInterest getById(long id) {
        return sessionFactory.getCurrentSession()
                .get(InvestmentInterest.class, id);
    }

    @Override
    public List<InvestmentInterest> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from InvestmentInterest", InvestmentInterest.class)
                .list();
    }
}
