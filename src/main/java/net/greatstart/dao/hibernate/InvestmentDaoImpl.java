package net.greatstart.dao.hibernate;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.model.Investment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvestmentDaoImpl implements InvestmentDao {
    private SessionFactory sessionFactory;

    @Autowired
    public InvestmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean create(Investment investment) {
        sessionFactory.getCurrentSession().save(investment);
        return true;
    }

    @Override
    public boolean delete(Investment investment) {
        sessionFactory.getCurrentSession().delete(investment);
        return true;
    }

    @Override
    public boolean update(Investment investment) {
        sessionFactory.getCurrentSession()
                .update(investment);
        return true;
    }

    @Override
    public Investment getById(long id) {
        return sessionFactory.getCurrentSession()
                .get(Investment.class, id);
    }

    @Override
    public List<Investment> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Investment", Investment.class)
                .list();


    }

    @Override
    public List<Investment> getAllByUserId(long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Investment WHERE user_id= :user_id", Investment.class)
                .setParameter("user_id", id)
                .list();
    }

    @Override
    public List<Investment> getAllByProjectId(long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Investment WHERE project_id= :project_id", Investment.class)
                .setParameter("project_id", id)
                .list();

    }
}
