package net.greatstart.services;

import net.greatstart.dao.EventDao;
import net.greatstart.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    private static final long ID = 1L;

    @Mock
    private EventDao eventDao;
    @InjectMocks
    private EventService eventService;
    private Event event;

    @Before
    public void setUp() {
        event = new Event();
    }

    @Test
    public void saveEventShouldSaveAndReturnEvent() throws Exception {
        when(eventDao.save(event)).thenReturn(event);
        assertEquals(event, eventService.saveEvent(event));
        verify(eventDao, times(1)).save(event);
    }

    @Test
    public void deleteEventShouldFindAndDeleteEvent() throws Exception {
        when(eventDao.findOne(ID)).thenReturn(event);
        eventService.deleteEvent(ID);
        verify(eventDao, times(1)).findOne(ID);
        verify(eventDao, times(1)).delete(event);
    }

    @Test
    public void getEventByIdShouldFindAndReturnEvent() throws Exception {
        when(eventDao.findOne(ID)).thenReturn(event);
        assertEquals(event, eventService.getEventById(ID));
        verify(eventDao, times(1)).findOne(ID);
    }

    @Test
    public void getAllEvents() throws Exception {
        List<Event> events = new ArrayList<>();
        events.add(event);
        when(eventDao.findAll()).thenReturn(events);
        assertTrue(eventService.getAllEvents().equals(events));
    }
}
