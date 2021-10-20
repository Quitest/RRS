package ru.pel.rrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@Entity
@Data
@Table(name = "rooms")
public class Room {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Digits(integer = 3, fraction = 0)
    @Id
    private long id;
    @NotEmpty(message = "{not.empty}")
    @Size(min = 2, max = 15, message = "{length.error}")
    private String classOfAccommodations;

    public Room() {
    }

    public Room(long id, LocalDateTime checkIn, String classOfAccommodations) {
        this.id = id;
        this.classOfAccommodations = classOfAccommodations;
    }

    public String getClassOfAccommodations() {
        return classOfAccommodations;
    }

    public void setClassOfAccommodations(String classOfAccommodations) {
        this.classOfAccommodations = classOfAccommodations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Проверка на пустоту, т.е. если все поля нулевые.
     *
     * @return true если все поля равны 0 и/или null, иначе false.
     */
    public boolean isEmpty() {
        return id == 0 &
                classOfAccommodations == null;
    }
}
