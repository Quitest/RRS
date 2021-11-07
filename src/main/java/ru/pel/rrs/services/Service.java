package ru.pel.rrs.services;

import org.springframework.web.server.MethodNotAllowedException;
import ru.pel.rrs.entities.stays.Room;
import ru.pel.rrs.entities.Guest;

import java.time.LocalDateTime;
import java.util.List;

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
    default long acquire(Guest guest, Room room, LocalDateTime start, LocalDateTime stop){
        throw  new UnsupportedOperationException("Еще не реализован");
    }

    /**
     * Поиск резерва по идентификатору
     *
     * @param id идентификатор искомого ресурса.
     * @return найденный ресурс.
     */
    //TODO Решить: если ресурс не найден, что возвращать null или пустой ресурс, или вообще Optional?
    default Room findById(long id){
        throw  new UnsupportedOperationException("Еще не реализован");
    }

    /**
     * Освободить ресурс, по идентификатору.
     *
     * @param id идентификатор освобождаемого ресурса.
     * @return true - ресурс освобожден успешно, false - в противном случае.
     */
    //TODO освобождать ресурс может только тот кто его резервировал?
    default boolean release(long id){
        throw  new UnsupportedOperationException("Еще не реализован");
    }

    //TODO Опционально можно реализовать:
    // Поиск резерва по критериям: пользователю, времени, ресурсу…
    // Поиск ближайшего свободного места по времени,…

    default List findBy(){
        throw  new UnsupportedOperationException("Еще не реализован");
    }

}
