package ru.pel.rrs.entities.stays.features;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "room_facility_name")})
@Getter
@Setter
public class RoomFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_facility_name")
    private String roomFacilityName;
    private boolean available;

//    @ManyToMany(mappedBy = "roomFacilitiesSet")
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stays_id"/*, nullable = false*/)
//    @JsonIgnore
    private Stays stays;

    public RoomFacility(String roomFacilityName, boolean available) {
        this.roomFacilityName = roomFacilityName;
        this.available = available;
    }

    public RoomFacility() {
    }

    public RoomFacility(long id, String roomFacilityName, boolean available, Stays stays) {
        this.id = id;
        this.roomFacilityName = roomFacilityName;
        this.available = available;
        this.stays = stays;
    }
}
