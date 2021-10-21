package ru.pel.rrs.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.DAO.GuestDAO;
import ru.pel.rrs.models.Guest;
import ru.pel.rrs.services.GuestService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestsControllerREST implements RESTController<Guest, Long> {
//    @Autowired
//    GuestDAO guestDAO;
    @Autowired
    GuestService guestService;

    @PostMapping
    public ResponseEntity<Guest> create(@RequestBody Guest entity) {
//        guestDAO.create(entity);
//        return ResponseEntity.ok(entity);
        return ResponseEntity.ok(guestService.save(entity));
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAll() {
//        return ResponseEntity.ok(guestDAO.getAll());
        return ResponseEntity.ok(guestService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Guest> getById(@PathVariable Long id) throws SQLException {
//        return ResponseEntity.ok(guestDAO.getById(id));
        return ResponseEntity.ok(guestService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Guest> delete(@PathVariable Long id) {
//        guestDAO.delete(id);
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Guest> update(@RequestBody Guest guest) throws SQLException {
        //WTF стоит ли делать запрос к БД для проверки?
        // это создает доп. нагрузку на БД. По идее, если не выскочило эксепшенов, то обновление прошло успешно и можно
        // вернуть значение параметра guest
//        guestDAO.update(guest);
//        return ResponseEntity.ok(guestDAO.getById(guest.getId()));
        return ResponseEntity.ok(guestService.save(guest));
    }
}
