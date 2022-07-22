package org.geekhub.oleg.location.model;

import org.springframework.stereotype.Component;

@Component
public class Location {
    private long id;
    private String city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
