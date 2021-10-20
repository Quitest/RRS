package ru.pel.rrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@Entity
@Table(name = "rooms")
public class Room extends Stays {
    //    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    @Digits(integer = 3, fraction = 0)
    private long id;
    @NotEmpty(message = "{not.empty}")
    @Size(min = 2, max = 15, message = "{length.error}")
    private String classOfAccommodations;

    public Room() {
        // Конструктор по-умолчанию используется, в частности, аннотацией @ModelAttribute в RoomsController
    }

    //Getters & Setters

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
    //FIXME логику перенести в слой сервиса
    public boolean isEmpty() {
        return id == 0 &
                classOfAccommodations == null;
    }
}
