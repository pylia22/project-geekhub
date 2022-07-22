package org.geekhub.oleg.order.model;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.user.model.User;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

@Component
public class Order {
    private long id;
    private Event event;
    private User user;
    private int quantity;

    public Order(Event event, User user, int quantity) {
        this.event = event;
        this.user = user;
        this.quantity = quantity;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
