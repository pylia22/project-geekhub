package org.geekhub.oleg.event.model;

public enum Category {
    CONCERT("Concert"),
    SHOW("Show"),
    CHILDREN("Children"),
    SPORT("Sport"),
    ADVENTURE("Adventure"),
    THEATRE("Theatre");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}

