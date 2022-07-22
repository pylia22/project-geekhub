package org.geekhub.oleg.event.service;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.event.repositoriy.EventRepositoryImpl;
import org.geekhub.oleg.location.model.Location;
import org.geekhub.oleg.location.repository.LocationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService<Event> {
    private AtomicInteger page = new AtomicInteger(1);
    private final EventRepositoryImpl eventRepository;
    private final LocationRepositoryImpl locationRepository;

    @Autowired
    public EventServiceImpl(EventRepositoryImpl eventRepository, LocationRepositoryImpl locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        page = new AtomicInteger(1);
        List<Event> eventList = new ArrayList<>();

        for (Event event : eventRepository.getAllEvents()) {
            if (event.getDate().isAfter(LocalDateTime.now())) {
                eventList.add(event);
            }
        }
        return eventList;
    }

    @Override

    public Event getEventById(long id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public List<Event> getEventsByCategory(String category) {
        category = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();

        return eventRepository.getEventsByCategory(category);
    }

    @Override
    public List<String> getAvailableCities() {
        List<String> locations = new ArrayList<>();

        for (Event event : eventRepository.getAvailableCities()) {
            if (event.getDate().isAfter(LocalDateTime.now())) {
                locations.add(event.getLocation().getCity());
            }
        }
        return locations;
    }

    @Override
    public List<Event> getEventsByCity(String city) {
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();

        return eventRepository.getEventsByCity(city);
    }

    @Override
    public void create(Event event) {
        event.setLocation(locationRepository.getCityID(event));
        eventRepository.create(event);
        page = new AtomicInteger(1);
    }

    @Override
    public void update(Event event, long id) {
        event.setLocation(locationRepository.getCityID(event));
        eventRepository.update(event, id);
        page = new AtomicInteger(1);
    }

    @Override
    public List<Location> getAllCities() {
        return locationRepository.getCities();
    }

    @Override
    public void delete(long id) {
        eventRepository.delete(id);
        page = new AtomicInteger(1);
    }

    @Override
    public List<Event> searchEvent(String searchValue) {
        return eventRepository.searchEvent(searchValue);
    }

    @Override
    public List<Event> loadMoreFeedbacks() {
        int size = 6;
        List<Event> eventList = new ArrayList<>();

        for (Event event : eventRepository.getAllEvents()) {
            if (event.getDate().isAfter(LocalDateTime.now())) {
                eventList.add(event);
            }
        }
        return eventList
                .stream()
                .skip(page.incrementAndGet() > 0 ? ((long) (page.get() - 1) * size) : 0)
                .limit(size).collect(Collectors.toList());
    }
}
