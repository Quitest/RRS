package ru.pel.rrs.entities.stays;

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
    void setFacilities(Set<Facilities> facilities);

    /**
     * Чем заняться в свободное время
     *
     * @param funThingsToDo
     */
    void setFunThingsToDo(Set<FunThingsToDo> funThingsToDo);

    /**
     * Тип питания.
     *
     * @param meals
     */
    void setMeals(Meals meals);

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
    void setRoomFacilities(RoomFacilities roomFacilities);

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
