package ru.pel.rrs.entities.stays;

import ru.pel.rrs.entities.stays.features.*;

import java.util.Set;

public class StaysBuilder implements Builder {

    private Set<Facilities> facilities;
    private Set<FunThingsToDo> funThingsToDo;
    private Set<Meals> meals;
    private PropertyType propertyType;
    private Set<RoomFacility> roomFacilities;
    private int roomNumber;
    private int number;

    public Stays getStays() {
        return new Stays(facilities, funThingsToDo, meals, propertyType, roomFacilities, roomNumber, number);
    }

    @Override
    public void setFacilities(Set<Facilities> facilities) {
        this.facilities = facilities;
    }

    @Override
    public void setFunThingsToDo(Set<FunThingsToDo> funThingsToDo) {
        this.funThingsToDo = funThingsToDo;
    }

    @Override
    public void setMeals(Set<Meals> meals) {
        this.meals = meals;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public void setRoomFacilities(Set<RoomFacility> roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    @Override
    public void setRoomsNumber(int roomsNumber) {
        this.roomNumber = roomsNumber;
    }
}
