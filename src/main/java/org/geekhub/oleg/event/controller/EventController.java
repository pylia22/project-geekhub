package org.geekhub.oleg.event.controller;

import org.geekhub.oleg.event.model.Category;
import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.event.service.EventServiceImpl;
import org.geekhub.oleg.feedback.FeedbackService;
import org.geekhub.oleg.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/event")
public class EventController {
    private final EventServiceImpl eventService;
    private final FeedbackService feedbackService;

    @Autowired
    public EventController(EventServiceImpl eventService, FeedbackService feedbackService) {
        this.eventService = eventService;
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public String showAllEvents(Model model) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("locations", eventService.getAvailableCities());
        model.addAttribute("events", eventService.getAllEvents().subList(0, 6));

        return "event/main";
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    @GetMapping("category/{category}")
    public String getEventsByCategory(@PathVariable("category") String category, Model model) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("locations", eventService.getAvailableCities());
        model.addAttribute("events", eventService.getEventsByCategory(category));

        return "event/main";
    }

    @GetMapping("/city/{city}")
    public String getEventsByCity(@PathVariable("city") String city, Model model) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("locations", eventService.getAvailableCities());
        model.addAttribute("events", eventService.getEventsByCity(city));

        return "event/main";
    }

    @GetMapping("/search")
    public String searchEvent(@RequestParam("keyWord") String searchValue, Model model) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("events", eventService.searchEvent(searchValue));

        return "event/main";
    }

    @GetMapping("/{id}")
    public String showEvent(@PathVariable("id") long id, Model model) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("event", eventService.getEventById(id));
        model.addAttribute("feedbacks", feedbackService.getAllFeedbacks(id));
        model.addAttribute("id", id);

        return "event/event";
    }

    @GetMapping("/add")
    public String addEvent(Model model) {
        model.addAttribute("cities", eventService.getAllCities());

        return "event/add";
    }

    @PostMapping("/add")
    public String addNewEvent(@RequestParam("eventName") String eventName, @RequestParam("type") String type,
                              @RequestParam("description") String description, @RequestParam("date") String date,
                              @RequestParam("city") String city, @RequestParam("price") double price,
                              @RequestParam("image") MultipartFile multipartFile) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Event event = new Event();
        Location location = new Location();
        event.setEventName(eventName);
        event.setCategory(Category.valueOf(type.toUpperCase()));
        event.setDescription(description);
        event.setDate(LocalDateTime.parse(date), dateTimeFormatter);
        location.setCity(city);
        event.setLocation(location);
        event.setPrice(price);

        try {
            event.setImageBytes(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        eventService.create(event);

        return "redirect:/event";
    }

    @GetMapping("/edit/{id}")
    public String showEditEvent(Model model, @PathVariable("id") long id) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("event", eventService.getEventById(id));
        model.addAttribute("cities", eventService.getAllCities());

        return "event/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEvent(@PathVariable long id, @RequestParam("eventName") String eventName,
                              @RequestParam("type") String type, @RequestParam("description") String description,
                              @RequestParam("date") String date, @RequestParam("city") String city,
                              @RequestParam("price") double price, @RequestParam("image") MultipartFile multipartFile) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Location location = new Location();
        Event event = eventService.getEventById(id);
        event.setEventName(eventName);
        event.setCategory(Category.valueOf(type.toUpperCase()));
        event.setDescription(description);
        event.setDate(LocalDateTime.parse(date), dateTimeFormatter);
        location.setCity(city);
        event.setLocation(location);
        event.setPrice(price);

        if (multipartFile != null) {
            try {
                event.setImageBytes(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        eventService.update(event, id);

        return "redirect:/event";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        eventService.delete(id);

        return "redirect:/event/";
    }

    @GetMapping("/loadMore")
    public String loadMoreFeedbacks(Model model) {
        model.addAttribute("admin", isAdmin());
        model.addAttribute("locations", eventService.getAvailableCities());
        model.addAttribute("events", eventService.loadMoreFeedbacks());

        return "event/load-more";
    }
}
