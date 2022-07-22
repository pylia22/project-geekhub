package org.geekhub.oleg.event.repositoriy;

import org.geekhub.oleg.event.model.Event;

import java.util.List;

public interface EventRepository<T> {
    List<Event> getAllEvents();

    Event getEventById(long id);

    List<Event> getEventsByCategory(String category);

    List<Event> getEventsByCity(String city);
    List<Event> getAvailableCities();

    void create(T t);

    void update(T t, long id);

    void delete(long id);

    List<Event> searchEvent(String searchValue);
}