package ru.pel.ResourceReservationSystem.models;

import java.time.LocalDateTime;

public class Reserve {
    private int id;
    private LocalDateTime eventDateTime;
    private String title;

    public Reserve() {
    }

    public Reserve(int id, LocalDateTime eventDateTime, String title) {
        this.id = id;
        this.eventDateTime = eventDateTime;
        this.title = title;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
