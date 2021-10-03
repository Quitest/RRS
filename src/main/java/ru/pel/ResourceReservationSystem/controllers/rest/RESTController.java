package ru.pel.ResourceReservationSystem.controllers.rest;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс простейшего REST контроллера. Методы возвращают ResponseEntity для гибкости, и попытки написать контролеры
 * возвращающие статус коды в соответствии со спецификацией HTTP.
 * Вполне можно обойтись и более простым механизмом, предлагаемым по-умолчанию Spring'ом.
 *
 * @param <T>
 */
public interface RESTController<T> {
    ResponseEntity<T> create(T entity);

    ResponseEntity<T> delete(Integer id);

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(Integer id);

    ResponseEntity<T> update(T entity);
}
