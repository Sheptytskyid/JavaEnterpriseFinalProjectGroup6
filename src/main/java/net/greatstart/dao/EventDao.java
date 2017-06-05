/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Event}.
 */
package net.greatstart.dao;


import net.greatstart.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventDao extends PagingAndSortingRepository<Event, Long> {

}
