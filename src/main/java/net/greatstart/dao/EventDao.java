package net.greatstart.dao;


import net.greatstart.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventDao extends CrudRepository<Event, Long> {

}
