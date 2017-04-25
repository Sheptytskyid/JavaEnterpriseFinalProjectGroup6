package net.greatstart.dao.hibernate;

import net.greatstart.dao.ProjectDao;
import net.greatstart.model.Project;

import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    @Override
    public boolean create(Project project) {
        return false;
    }

    @Override
    public boolean delete(Project project) {
        return false;
    }

    @Override
    public boolean update(Project project) {
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
