package net.greatstart.dao;

import net.greatstart.model.Project;

import java.util.List;

public interface ProjectDao extends GenericDao<Project> {
    List<Project> getNRecords(int n);
}
