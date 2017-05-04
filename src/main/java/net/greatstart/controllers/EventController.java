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

    public void createEvent(Event event) {
        eventService.createEvent(event);
    }

    public void updateEvent(long id) {
        eventService.updateEvent(id);
    }

    public void deleteEvent(long id) {
        eventService.deleteEvent(id);
    }

    public Event getEventbyId(long id) {
        return eventService.getEventById(id);
    }

    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
