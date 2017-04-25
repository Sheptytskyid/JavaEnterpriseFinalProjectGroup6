package net.greatstart.controller;

import net.greatstart.model.Event;
import net.greatstart.services.EventService;

import java.util.List;

public class EventController {

    private EventService eventService;

    public boolean createEvent(Event event) {
        return eventService.createEvent(event);
    }

    public boolean updateEvent(long id) {
        return eventService.updateEvent(id);
    }

    public boolean deleteEvent(long id) {
        return eventService.deleteEvent(id);
    }

    public Event getEventbyId(long id) {
        return eventService.getEventById(id);
    }

    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
