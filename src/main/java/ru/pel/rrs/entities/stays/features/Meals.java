package ru.pel.rrs.entities.stays.features;

public enum Meals {
    KITCHEN("Kitchen facilities"),

    BREAKFAST("Breakfast Included"),

    ALL("All meals included"),

    BREAKFAST_LUNCH("Breakfast & lunch included"),

    BREAKFAST_DINNER("Breakfast & dinner included");

    String type;
    Meals(String type){
        this.type = type;
    }

    public String getValue(){
        return type;
    }
}
