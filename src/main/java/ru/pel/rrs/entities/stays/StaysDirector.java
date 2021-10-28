package ru.pel.rrs.entities.stays;

import ru.pel.rrs.entities.stays.features.Meals;
import ru.pel.rrs.entities.stays.features.PropertyType;
import ru.pel.rrs.entities.stays.features.RoomFacilities;

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

//        Set<RoomFacilities> roomFacilities = new HashSet<>();
//        roomFacilities.add(new RoomFacilities())
//        builder.setRoomFacilities();
    }
}
