package net.greatstart.services;

import net.greatstart.dao.EventDao;
import net.greatstart.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public boolean createEvent(Event event) {
        return eventDao.create(event);
    }

    public boolean updateEvent(long id) {
        Event event = getEventById(id);
        return eventDao.update(event);
    }

    public boolean deleteEvent(long id) {
        Event event = getEventById(id);
        return eventDao.delete(event);
    }

    public Event getEventById(long id) {
        return eventDao.getById(id);
    }

    public List<Event> getAllEvents() {
        return eventDao.getAll();
    }
}
