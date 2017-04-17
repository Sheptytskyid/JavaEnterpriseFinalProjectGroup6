package net.greatstart.dao.hibernate;

import net.greatstart.dao.UserDao;
import net.greatstart.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getById(long id) {
        User result;
        SessionFactory sessionFactory;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Cannot create Session Factory", ex);
        }
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                result = session.get(User.class, id);
                session.getTransaction().commit();
            }catch (HibernateException e){
                throw new RuntimeException("Cannot connect to DB", e);
            }
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result;
        SessionFactory sessionFactory;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Cannot create Session Factory", ex);
        }
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                result = session.createQuery("select u from User u").list();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException("Cannot connect to DB", e);
            }
        }
        return result;
    }
}

