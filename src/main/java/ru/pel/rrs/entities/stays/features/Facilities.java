package ru.pel.rrs.entities.stays.features;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Facilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String facilityName;
    private boolean available;

    @ManyToMany(mappedBy = "facilities")
    @JsonIgnore
    private Set<Stays> staysSet;
}
/*
Перечень удобств с сайта booking.com
    Non-smoking rooms
    Family rooms
    Parking
    Free WiFi
        24-hour front desk
    Room service
    Spa
    Airport shuttle
    Pet friendly
    Fitness center
    Restaurant
    Facilities for disabled guests
    Swimming pool
    Electric vehicle charging station
    */
