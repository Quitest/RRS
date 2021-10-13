package ru.pel.rrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
public class Room {
    @Digits(integer = 3, fraction = 0)
    private long id;
    @NotEmpty(message = "Должно быть не пустым")
    @Size(min = 2, max = 15, message = "Длинна должна быть от 2 до 15 символов")
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
