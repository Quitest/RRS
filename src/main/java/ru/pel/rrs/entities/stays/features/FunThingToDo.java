package ru.pel.rrs.entities.stays.features;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import java.util.Set;

/**
 * Веселые занятия, досуг.
 */
@Entity
@Getter
@Setter
public class FunThingToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String funThing;
    /**
     * Удаленность в километрах.
     */
    private float remoteness;

    @ManyToMany(mappedBy = "funThingsToDo")
//    @JsonIgnore
    private Set<Stays> staysSet;
}
