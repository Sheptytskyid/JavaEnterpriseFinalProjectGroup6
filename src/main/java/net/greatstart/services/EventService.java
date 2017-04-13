package net.greatstart.services;

import net.greatstart.dao.EventDao;
import net.greatstart.model.Event;
import java.util.List;

public class EventService {

    private EventDao eventDao;

    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public void createEvent(Event event) {
        eventDao.create(event);
    }

    public Event getEventById(long id) {
        return eventDao.getById(id);
    }

    public boolean updateEvent(Event event) {
        return eventDao.update(event);
    }

    public List<Event> getAllEvents() {
        return eventDao.getAll();
    }
}
