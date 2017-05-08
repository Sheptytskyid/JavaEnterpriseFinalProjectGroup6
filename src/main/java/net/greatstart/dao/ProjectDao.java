package net.greatstart.dao;

import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectDao extends CrudRepository<Project, Long> {

    List<Project> findAll(Pageable numberOfRecords);

    List<Project> findByOwner(User owner);
}
