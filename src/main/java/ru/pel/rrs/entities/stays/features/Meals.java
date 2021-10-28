package ru.pel.rrs.entities.stays.features;

import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Meals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mealName;
    private boolean available;

    @ManyToMany(mappedBy = "mealSet")
    private Set<Stays> staysSet;
}
