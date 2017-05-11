package net.greatstart.dao;


import net.greatstart.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventDao extends PagingAndSortingRepository<Event, Long> {

}
