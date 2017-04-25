package net.greatstart.dao.hibernate;

import net.greatstart.dao.EventDao;
import net.greatstart.model.Event;

import java.util.List;

public class EventDaoImpl implements EventDao {

    @Override
    public boolean create(Event event) {
        return false;
    }

    @Override
    public boolean delete(Event event) {
        return false;
    }

    @Override
    public boolean update(Event event) {
        return false;
    }

    @Override
    public Event getById(long id) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }

}
