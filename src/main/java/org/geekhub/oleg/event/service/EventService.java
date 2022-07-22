package org.geekhub.oleg.event.service;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.location.model.Location;

import java.util.List;

public interface EventService<T> {
    List<Event> getAllEvents();

    List<Event> getEventsByCategory(String category);

    List<Event> getEventsByCity(String city);

    Event getEventById(long id);

    List<String> getAvailableCities();

    void create(T t);

    void update(T t, long id);

    List<Location> getAllCities();

    void delete(long id);

    List<Event> searchEvent(String searchValue);
    List<Event> loadMoreFeedbacks();
}
