package ru.pel.rrs.entities.stays;

import ru.pel.rrs.entities.stays.features.PropertyType;

public class StaysDirector {
    public void buildHouse(Builder builder){
        builder.setPropertyType(PropertyType.HOUSE);
    }
}
