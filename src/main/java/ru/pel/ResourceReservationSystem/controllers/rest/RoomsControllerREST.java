package ru.pel.ResourceReservationSystem.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.RoomDAO;
import ru.pel.ResourceReservationSystem.exceptions.ErrorInfo;
import ru.pel.ResourceReservationSystem.models.Room;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

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
        if (room == null || room.getId() == 0) {
            String msg = "Комнаты с ID=" + id + " не существует";
            throw new NoSuchElementException(msg);
        }
        return ResponseEntity.ok(room);
    }

    @PutMapping
    public ResponseEntity<Room> update(@RequestBody Room room) {
        roomDAO.update(room);
        return ResponseEntity.ok(room);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseBody
    public ErrorInfo handleSQLExceptions(HttpServletRequest request, Exception exception){
        return new ErrorInfo(request.getRequestURL().toString(), exception);
    }

}

