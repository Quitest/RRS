package ru.pel.rrs.dto;

import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.Reserve;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
public class GuestDTO {
    private long id;
    private String name;
    private String middleName;
    private String lastname;
    @Min(value = 18, message = "{minimal.guest.age}")
    private int age;
    private Set<Reserve> reserves;

    public GuestDTO() {
        // Конструктор по-умолчанию используется, в частности, аннотацией @ModelAttribute в GuestsController
    }

    @Override
    public String toString() {
        return lastname +
                " " +
                name +
                " " +
                middleName;
    }
}
