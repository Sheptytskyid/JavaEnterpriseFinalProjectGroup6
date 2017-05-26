package net.greatstart.controllers;

import net.greatstart.services.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController controller;

    @Test
    public void Test() throws Exception {
        EventController eventController = controller;
    }


}