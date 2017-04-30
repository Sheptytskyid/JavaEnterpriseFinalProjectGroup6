package net.greatstart.dao.hibernate;

import net.greatstart.dao.RoleDao;
import net.greatstart.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean create(Role item) {
        return false;
    }

    @Override
    public boolean update(Role item) {
        return false;
    }

    @Override
    public boolean delete(Role item) {
        return false;
    }

    @Override
    public Role getById(long id) {
        return sessionFactory.getCurrentSession()
                .get(Role.class, id);
    }

    @Override
    public List<Role> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Role getByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
