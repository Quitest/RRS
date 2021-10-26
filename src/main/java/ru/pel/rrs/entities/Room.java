package ru.pel.rrs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room extends Stays {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Digits(integer = 3, fraction = 0)
    @Positive(message = "{must.be.positive}")
    private long id;

    @NotEmpty(message = "{not.empty}")
    @Size(min = 2, max = 15, message = "{length.error}")
    @Column(name = "class_of_accommodations")
    private String classOfAccommodations;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
//    @JoinColumn(name = "reserve_id")
    private Set<Reserve> reserveSet;

    public Room() {
        // Явное указание на наличие конструктора по-умолчанию.
    }

    /**
     * Проверка на пустоту, т.е. если все поля нулевые.
     *
     * @return true если все поля равны 0 и/или null, иначе false.
     */
    //FIXME логику перенести в слой сервиса
    public boolean isEmpty() {
        return id == 0 &&
                classOfAccommodations == null;
    }
}
