package ru.pel.rrs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pel.rrs.annotations.ConsistentDates;
import ru.pel.rrs.entities.stays.Room;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@ConsistentDates(firstField = "checkIn", secondField = "checkOut", message = "{date.checkIn.must.be.before.checkOut}")
@Entity
@Table(name = "reserves")
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stays_id")
    private Stays stays;

    public Reserve() {
        // Конструктор по-умолчанию используется, в частности, аннотацией @ModelAttribute в ReservesController
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
                checkOut == null;// &
//                guestId == 0 &
//                roomId == 0;
    }
}
