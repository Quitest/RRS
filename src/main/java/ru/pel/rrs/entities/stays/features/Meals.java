package ru.pel.rrs.entities.stays.features;

import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Meals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "meal_name")
    private String mealName;
    private boolean available;

    //    @ManyToMany(mappedBy = "meals")
//    @JsonIgnore
//    private Set<Stays> stays;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stays_id")
    private Stays stays;

    public Meals(String mealName, boolean available) {
        this.mealName = mealName;
        this.available = available;
    }

    public Meals() {
    }

    public Meals(long id, String mealName, boolean available, Stays stays) {
        this.id = id;
        this.mealName = mealName;
        this.available = available;
        this.stays = stays;
    }
}
