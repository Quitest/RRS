package ru.pel.rrs.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.DAO.RoomDAO;
import ru.pel.rrs.models.Room;

import java.sql.SQLException;
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
    public ResponseEntity<Room> getById(@PathVariable Integer id) throws SQLException {
        Room room = roomDAO.getById(id);
        return ResponseEntity.ok(room);
    }

    @PutMapping
    public ResponseEntity<Room> update(@RequestBody Room room) throws SQLException {
        roomDAO.update(room);
        return ResponseEntity.ok(room);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = NoSuchElementException.class)
//    @ResponseBody
//    public ExceptionBody handleSQLExceptions(HttpServletRequest request, Exception exception){
//        return new ExceptionBody(request.getRequestURL().toString(), exception);
//    }

}

