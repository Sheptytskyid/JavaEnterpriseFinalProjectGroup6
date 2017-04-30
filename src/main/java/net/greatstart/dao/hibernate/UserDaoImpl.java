package net.greatstart.dao.hibernate;

import net.greatstart.dao.UserDao;
import net.greatstart.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean create(User user) {
        sessionFactory.getCurrentSession()
                .save(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        sessionFactory.getCurrentSession()
                .delete(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        sessionFactory.getCurrentSession()
                .update(user);
        return true;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getById(long id) {
        return sessionFactory.getCurrentSession()
                .get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class)
                .list();
    }
}
