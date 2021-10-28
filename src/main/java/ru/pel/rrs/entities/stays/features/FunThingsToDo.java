package ru.pel.rrs.entities.stays.features;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class FunThingsToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String funThing;
    private boolean available;

    @ManyToMany(mappedBy = "funThingsToDo")
    @JsonIgnore
    private Set<Stays> staysSet;
}
