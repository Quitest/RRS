package ru.pel.ResourceReservationSystem.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.RoomDAO;
import ru.pel.ResourceReservationSystem.models.Room;

import java.util.List;

/*
По сути весь функционал данного контроллера можно было бы реализовать в RoomsController. Достаточно было бы ввести
соответствующие методы с аннотациями, указав в аннотациях consumes'ов и produces'ов. Либо вместо produces навесить
@ResponseBody. Однако, с моей точки зрения, это очень сильно затруднит читаемость кода и его отладку. Особенно если
сложность кода и функционал приложения будет развиваться. Плюс ко всему сказанному, точнее еще одним минусом, при таком
подходе будет гораздо сложнее добавлять новые возможности в части принимать/отвечать в новых форматах типа YAML и др.

Поэтому сейчас будем пытаться придерживаться принципа "единственной ответственности" - каждый класс решает только свою,
единственную задачу.
*/

/**
 * Идя реализации универсального REST контроллера взята с сайта <a href=https://stackoverflow.com/questions/35270660/accepting-returning-xml-json-request-and-response-spring-mvc>stackoverflow.com</a>
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomsControllerREST implements RESTController<Room> {
    @Autowired
    RoomDAO roomDAO;

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room) {
        roomDAO.create(room);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Room> delete(@PathVariable Integer id) {
        roomDAO.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomDAO.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Room> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomDAO.getById(id));
    }

    @PutMapping
    public ResponseEntity<Room> update(@RequestBody Room room) {
        roomDAO.update(room);
        return ResponseEntity.ok(room);
    }

}
