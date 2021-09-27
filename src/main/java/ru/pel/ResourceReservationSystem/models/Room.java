package ru.pel.ResourceReservationSystem.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class Room {
    @Digits(integer = 3, fraction = 0)
    private int id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkIn;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkOut;

    @NotEmpty(message = "Должно быть не пустым")
    @Size(min = 2, max = 15, message = "Длинна должна быть от 2 до 15 символов")
    private String classOfAccommodations;

    public Room() {
    }

    public Room(int id, LocalDateTime checkIn, String classOfAccommodations) {
        this.id = id;
        this.checkIn = checkIn;
        this.classOfAccommodations = classOfAccommodations;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public String getClassOfAccommodations() {
        return classOfAccommodations;
    }

    public void setClassOfAccommodations(String classOfAccommodations) {
        this.classOfAccommodations = classOfAccommodations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
