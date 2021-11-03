package ru.pel.rrs.entities.bulders;

import ru.pel.rrs.entities.stays.features.*;

import java.util.Set;

/**
 * Объявляет все возможные шаги и этапы конфигурации номера/жилья
 */
public interface Builder {
    /**
     * Удобства
     *
     * @param facilities
     */
    void setFacilities(Set<Facility> facilities);

    /**
     * Чем заняться в свободное время
     *
     * @param funThingsToDo
     */
    void setFunThingsToDo(Set<FunThingToDo> funThingsToDo);

    /**
     * Тип питания.
     *
     * @param meals
     */
    void setMeals(Set<Meals> meals);

    /**
     * Тип собственности/размещения
     *
     * @param propertyType
     */
    void setPropertyType(PropertyType propertyType);

    /**
     * Удобства в номере/жилье
     *
     * @param roomFacilities
     */
    void setRoomFacilities(Set<RoomFacility> roomFacilities);

    /**
     * Количество комнат
     *
     * @param roomsNumber
     */
    void setRoomsNumber(int roomsNumber);

    /**
     * Номер комнаты/дома/койки/жЫлья.
     * @param number
     */
    void setNumber(int number);
}
