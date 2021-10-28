package ru.pel.rrs.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pel.rrs.entities.Reserve;
import ru.pel.rrs.entities.stays.features.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaysDTO {
    private long id;
    private Set<Facilities> facilities;
    private Set<FunThingsToDo> funThingsToDo;
    private Set<Meals> meals;
    private PropertyType propertyType;
    private Set<Reserve> reserveSet;
    private Set<RoomFacilities> roomFacilitiesSet;
    private int roomNumber;
    private int number;
}
