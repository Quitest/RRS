package ru.pel.rrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pel.rrs.annotations.ConsistentDates;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@ConsistentDates(firstField = "checkIn", secondField = "checkOut", message = "{date.checkIn.must.be.before.checkOut}")
@Entity
@Table(name = "reserves")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "check_in")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "{date.must.be.in.future}")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "{date.must.be.in.future}")
    private LocalDateTime checkOut;

    @Column(name = "guest_id")
    private long guestId;

    @Column(name = "room_id")
    private long roomId;

    public Reserve() {
        // Конструктор по-умолчанию используется, в частности, аннотацией @ModelAttribute в ReservesController
    }

    //Getters & Setters
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

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    /**
     * Проверка на пустоту, т.е. если все поля нулевые.
     *
     * @return true если все поля равны 0 и/или null, иначе false.
     */
    //FIXME логику перенести в слой сервиса
    public boolean isEmpty() {
        return id == 0 &
                checkIn == null &
                checkOut == null &
                guestId == 0 &
                roomId == 0;
    }
}
