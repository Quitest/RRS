package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.RoomDAO;
import ru.pel.ResourceReservationSystem.models.Room;

import java.util.List;

/*
По сути весь функционал данного контроллера можно было бы реализовать в RoomsController. Достаточно было бы ввести
соответствующие методы с аннотациями, указав в аннотациях consumes'ов и produces'ов. Либо вместо produces навесить
@ResponseBody. Однако, с моей точки зрения, это очень сильно затруднит читаемость кода и его отладку. Особенно если
сложность кода и функционал приложения будет разростаться. Плюс ко всему сказанному, точнее еще одним минусом, при таком
подходе будет гораздо сложнее добавлять новые возможности в части принимать/отвечать в новых форматах типа YAML и др.

Поэтому сейчас будем пытаться придерживаться принципа "единственной ответственности" - каждый класс решает только свою,
единственную задачу.
*/

/**
 * Идя реализации универсального REST контроллера взята с сайта <a href=https://stackoverflow.com/questions/35270660/accepting-returning-xml-json-request-and-response-spring-mvc>stackoverflow.com</a>
 */
@RestController
@RequestMapping(path = "/api/rooms")
public class RoomsControllerREST {
    @Autowired
    RoomDAO roomDAO;

    @PostMapping
    public void createNewRoom(@RequestBody Room room) {
        roomDAO.create(room);
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room){
        roomDAO.update(room);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(room);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable int id) {
        roomDAO.delete(id);
    }

    @GetMapping
    public List<Room> getAll() {
        return roomDAO.getAll();
    }

    @GetMapping("{id}")
    public Room getRoomInfo(@PathVariable int id) {
        return roomDAO.getById(id);
    }

}
