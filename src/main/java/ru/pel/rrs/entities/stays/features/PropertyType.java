package ru.pel.rrs.entities.stays.features;

public enum PropertyType {
    HOUSE("house"),
    ROOM("room"),
    APARTMENT("apartment");

    String type;

    PropertyType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
