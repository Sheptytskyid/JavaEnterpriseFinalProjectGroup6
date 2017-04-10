package ua.goit.group6.services;

import ua.goit.group6.dao.EventDao;
import ua.goit.group6.model.Event;
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
