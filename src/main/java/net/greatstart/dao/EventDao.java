package net.greatstart.dao;

import net.greatstart.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Event}.
 */

public interface EventDao extends PagingAndSortingRepository<Event, Long> {

}
