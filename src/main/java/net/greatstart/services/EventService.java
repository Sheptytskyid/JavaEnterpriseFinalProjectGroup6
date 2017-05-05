package net.greatstart.services;

import net.greatstart.dao.EventDao;
import net.greatstart.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventService {

    private EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public void saveEvent(Event event) {
        eventDao.save(event);
    }

    public void deleteEvent(long id) {
        Event event = getEventById(id);
        eventDao.delete(event);
    }

    public Event getEventById(long id) {
        return eventDao.findOne(id);
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventDao.findAll().forEach(events::add);
        return events;
    }
}
