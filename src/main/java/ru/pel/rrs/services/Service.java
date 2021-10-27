package ru.pel.rrs.services;

import ru.pel.rrs.entities.stays.Room;
import ru.pel.rrs.entities.Guest;

import java.time.LocalDateTime;

public interface Service {
    /**
     * Резервировать ресурс. Если ресурс свободен возвращать идентификатор, если нет бросать ошибку.
     *
     * @param guest     Пользователь, пытающийся зарезервировать ресурс.
     * @param room резервируемый ресурс.
     * @param start    дата и время начала резервирования ресурса.
     * @param stop     дата и время окончания резервирования ресурса.
     * @return id ресурса, если он свободен.
     * @throws Throwable Если ресурс занят
     */
    //TODO написать свое исключение. Вариант - конкретный тип исключения может бросать конкретный тип ресурса.
    long acquire(Guest guest, Room room, LocalDateTime start, LocalDateTime stop) throws Throwable;

    /**
     * Поиск резерва по идентификатору
     *
     * @param id идентификатор искомого ресурса.
     * @return найденный ресурс.
     */
    //TODO Решить: если ресурс не найден, что возвращать null или пустой ресурс, или вообще Optional?
    Room findById(long id);

    /**
     * Освободить ресурс, по идентификатору.
     *
     * @param id идентификатор освобождаемого ресурса.
     * @return true - ресурс освобожден успешно, false - в противном случае.
     */
    //TODO освобождать ресурс может только тот кто его резервировал?
    boolean release(long id);

    //TODO Опционально можно реализовать:
    // Поиск резерва по критериям: пользователю, времени, ресурсу…
    // Поиск ближайшего свободного места по времени,…
}
