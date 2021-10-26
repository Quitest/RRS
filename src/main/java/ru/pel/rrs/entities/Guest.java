// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.rrs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"empty"} //Игнор результата работы isEmpty(), если не будет, то в ответах будет boolean поле empty
)
@Entity
@Table(name = "guests")
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "middle_name")
    private String middleName;

    private String lastname;

    @Min(value = 18, message = "{minimal.guest.age}")
    private int age;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private Set<Reserve> reserves;

    public Guest() {
        // Конструктор по-умолчанию используется, в частности, аннотацией @ModelAttribute в GuestsController
    }

    //FIXME логику перенести в слой сервиса
    public boolean isEmpty() {
        return id == 0 &
                name == null &
                middleName == null &
                lastname == null &
                age == 0;
    }
}