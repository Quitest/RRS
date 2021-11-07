package ru.pel.rrs.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pel.rrs.entities.Reserve;
import ru.pel.rrs.entities.stays.features.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StaysDTO {
    private Set<FacilityDTO> facilities;
    private Set<FunThingToDoDTO> funThingsToDo;
    private Set<MealsDTO> meals;
    private PropertyType propertyType;
    private Set<ReserveDTO> reserveSet;
    private Set<RoomFacilityDTO> roomFacilitiesSet;
    private int roomNumber;
    private int number;
}
