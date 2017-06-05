package net.greatstart.dao;

import net.greatstart.model.Project;
import net.greatstart.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Project}.
 */

public interface ProjectDao extends PagingAndSortingRepository<Project, Long> {

    List<Project> findByOwner(User owner);
}
