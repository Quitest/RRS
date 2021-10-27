package ru.pel.rrs.entities.stays;

import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.stays.features.*;

import javax.persistence.*;
import java.util.Set;

/**
 * По задумке жилье в самом общем смысле: номер, комната, квартира, дом, бунгало, палатко-место и т.д.
 */
@Entity
@Getter
@Setter
public class Stays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stays_facilities",
            joinColumns = @JoinColumn(name = "stays_id"),
            inverseJoinColumns = @JoinColumn(name = "facilities_id")
    )
    private Set<Facilities> facilities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stays_funThingsToDo",
            joinColumns = @JoinColumn(name = "stays_id"),
            inverseJoinColumns = @JoinColumn(name = "fun_thing_id")
    )
    private Set<FunThingsToDo> funThingsToDo;
    //TODO
    private Meals meals;
    //TODO
    private PropertyType propertyType;
    //TODO
    private RoomFacilities roomFacilities;
    private int roomNumber;
    private int number;

    public Stays() {
    }

    public Stays(Set<Facilities> facilities, Set<FunThingsToDo> funThingsToDo, Meals meals, PropertyType propertyType,
                 RoomFacilities roomFacilities, int roomNumber, int number) {
        this.facilities = facilities;
        this.funThingsToDo = funThingsToDo;
        this.meals = meals;
        this.propertyType = propertyType;
        this.roomFacilities = roomFacilities;
        this.roomNumber = roomNumber;
        this.number = number;
    }
}
