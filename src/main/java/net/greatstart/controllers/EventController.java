package net.greatstart.controllers;

import net.greatstart.model.Event;
import net.greatstart.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

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
