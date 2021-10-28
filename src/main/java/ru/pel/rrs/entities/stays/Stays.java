package ru.pel.rrs.entities.stays;

import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.Reserve;
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
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private Set<Facilities> facilities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stays_fun_things_to_do",
            joinColumns = @JoinColumn(name = "stays_id"),
            inverseJoinColumns = @JoinColumn(name = "fun_thing_id")
    )
    private Set<FunThingsToDo> funThingsToDo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stays_meals",
            joinColumns = @JoinColumn(name = "stays_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private Set<Meals> mealSet;
    private PropertyType propertyType;

    @OneToMany(mappedBy = "stays", fetch = FetchType.LAZY)
    private Set<Reserve> reserveSet;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stays_room_facilities",
            joinColumns = @JoinColumn(name = "stays_id"),
            inverseJoinColumns = @JoinColumn(name = "room_facility_id")
    )
    private Set<RoomFacilities> roomFacilitiesSet;
    private int roomNumber;
    private int number;

    public Stays() {
    }

    public Stays(Set<Facilities> facilities, Set<FunThingsToDo> funThingsToDo, Set<Meals> mealSet, PropertyType propertyType,
                 Set<RoomFacilities> roomFacilitiesSet, int roomNumber, int number) {
        this.facilities = facilities;
        this.funThingsToDo = funThingsToDo;
        this.mealSet = mealSet;
        this.propertyType = propertyType;
        this.roomFacilitiesSet = roomFacilitiesSet;
        this.roomNumber = roomNumber;
        this.number = number;
    }
}
