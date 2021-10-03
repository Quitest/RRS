package ru.pel.ResourceReservationSystem.controllers.rest;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <p>Идея реализации универсального (по формату принимаемых и возвращаемых данных) REST-контроллера взята с сайта
 * <a href=https://stackoverflow.com/questions/35270660/accepting-returning-xml-json-request-and-response-spring-mvc>stackoverflow.com</a>
 * </p>
 * <p>Интерфейс простейшего REST контроллера. Методы возвращают ResponseEntity для гибкости, и попытки написать контролеры
 * возвращающие статус коды в соответствии со спецификацией HTTP.
 * Вполне можно обойтись и более простым механизмом, предлагаемым по-умолчанию Spring'ом.
 * </p>
 *
 * @param <T> тип возвращаемого объекта
 */
public interface RESTController<T> {
    ResponseEntity<T> create(T entity);

    ResponseEntity<T> delete(Integer id);

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(Integer id);

    ResponseEntity<T> update(T entity);
}
