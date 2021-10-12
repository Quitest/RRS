package ru.pel.ResourceReservationSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pel.ResourceReservationSystem.annotations.ConsistentDates;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@ConsistentDates(firstField = "checkIn", secondField = "checkOut", message = "Не верная последовательность дат")
public class Reserve {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "Дата должна быть в будущем")
    private LocalDateTime checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "Дата должна быть в будущем")
    private LocalDateTime checkOut;
    private int guestId;
    private int roomId;

    public Reserve() {
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

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Проверка на пустоту, т.е. если все поля нулевые.
     *
     * @return true если все поля равны 0 и/или null, иначе false.
     */
    public boolean isEmpty() {
        return id == 0 &
                checkIn == null &
                checkOut == null &
                guestId == 0 &
                roomId == 0;
    }
}
