package org.geekhub.oleg.feedback;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Feedback {
    private long id;
    private long eventId;
    private String userName;
    private String message;
    private LocalDateTime date;

    public Feedback() {
    }

    public Feedback(long eventId, String userName, String message, LocalDateTime date) {
        this.eventId = eventId;
        this.userName = userName;
        this.message = message;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
