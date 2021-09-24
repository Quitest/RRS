package ru.pel.ResourceReservationSystem.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Room {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventDateTime;
    private String title;

    public Room() {
    }

    public Room(int id, LocalDateTime eventDateTime, String title) {
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
