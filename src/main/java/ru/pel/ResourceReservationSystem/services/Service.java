package ru.pel.ResourceReservationSystem.services;

import ru.pel.ResourceReservationSystem.models.Resource;
import ru.pel.ResourceReservationSystem.models.User;

import java.time.LocalDateTime;

public interface Service {
    /**
     * Резервировать ресурс. Если ресурс свободен возвращать идентификатор, если нет бросать ошибку.
     *
     * @param user     Пользователь, пытающийся зарезервировать ресурс.
     * @param resource резервируемый ресурс.
     * @param start    дата и время начала резервирования ресурса.
     * @param stop     дата и время окончания резервирования ресурса.
     * @return id ресурса, если он свободен.
     * @throws Throwable Если ресурс занят
     */
    //TODO написать свое исключение. Вариант - конкретный тип исключения может бросать конкретный тип ресурса.
    long acquire(User user, Resource resource, LocalDateTime start, LocalDateTime stop) throws Throwable;

    /**
     * Поиск резерва по идентификатору
     *
     * @param id идентификатор искомого ресурса.
     * @return найденный ресурс.
     */
    //TODO Решить: если ресурс не найден, что возвращать null или пустой ресурс, или вообще Optional?
    Resource findById(long id);

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
