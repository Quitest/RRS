package ru.pel.rrs.entities.stays;

import ru.pel.rrs.entities.stays.features.Meals;
import ru.pel.rrs.entities.stays.features.PropertyType;

import java.util.HashSet;
import java.util.Set;

public class StaysDirector {
    private StaysDirector(){}

    public static void buildHouse(Builder builder){
        builder.setPropertyType(PropertyType.HOUSE);
        builder.setRoomsNumber(4);

        Set<Meals> meals = new HashSet<>();
        meals.add(new Meals("кухня", true));
        builder.setMeals(meals);

//        Set<RoomFacility> roomFacilities = new HashSet<>();
//        roomFacilities.add(new RoomFacility())
//        builder.setRoomFacilities();
    }
}
