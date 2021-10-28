package ru.pel.rrs.dto;

import ru.pel.rrs.entities.Reserve;
import ru.pel.rrs.entities.stays.features.*;

import javax.persistence.*;
import java.util.Set;

public class StaysDTO {
    private long id;
    private Set<Facilities> facilities;
    private Set<FunThingsToDo> funThingsToDo;
    private Set<Meals> mealSet;
    private PropertyType propertyType;
    private Set<Reserve> reserveSet;
    private Set<RoomFacilities> roomFacilitiesSet;
    private int roomNumber;
    private int number;
}
