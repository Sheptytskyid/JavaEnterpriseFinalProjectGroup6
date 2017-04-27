package net.greatstart.dao.hibernate;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    private SessionFactory sessionFactory;

    @Autowired
    public ProjectDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean create(Project project) {
        sessionFactory.getCurrentSession()
                .save(project);
        return true;
    }

    @Override
    public boolean delete(Project project) {
        sessionFactory.getCurrentSession()
                .delete(project);
        return true;
    }

    @Override
    public boolean update(Project project) {
        sessionFactory.getCurrentSession()
                .update(project);
        return true;
    }

    @Override
    public Project getById(long id) {
        return sessionFactory.getCurrentSession()
                .get(Project.class, id);
    }

    @Override
    public List<Project> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Project", Project.class)
                .list();
    }

    @Override
    public List<Project> getNRecords(int n) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Project", Project.class)
                .setMaxResults(n)
                .list();
    }
}
