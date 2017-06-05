/**
 * A controller for events (to be implemented). See {@link net.greatstart.model.Event}
 */
package net.greatstart.controllers;

import net.greatstart.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    //TODO: to be implemented
}
