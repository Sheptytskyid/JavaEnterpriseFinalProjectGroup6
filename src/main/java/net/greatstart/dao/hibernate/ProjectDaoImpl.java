package net.greatstart.dao.hibernate;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @Override
    public boolean create(Project item) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(Project item) {
        return false;
    }

    @Override
    public Project getById(long id) {
        return null;
    }

    @Override
    public List<Project> getAll() {
        return null;
    }
}
