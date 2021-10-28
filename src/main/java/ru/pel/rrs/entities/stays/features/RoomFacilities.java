package ru.pel.rrs.entities.stays.features;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class RoomFacilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomFacilityName;
    private boolean available;

//    @ManyToMany(mappedBy = "roomFacilitiesSet")
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stays_id")
    private Stays stays;
}
