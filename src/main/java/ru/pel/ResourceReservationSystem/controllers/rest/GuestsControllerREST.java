package ru.pel.ResourceReservationSystem.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.GuestDAO;
import ru.pel.ResourceReservationSystem.models.Guest;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestsControllerREST implements RESTController<Guest> {
    @Autowired
    GuestDAO guestDAO;

    @PostMapping
    public ResponseEntity<Guest> create(@RequestBody Guest entity) {
        guestDAO.create(entity);
        return ResponseEntity.ok(entity);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAll() {
        return ResponseEntity.ok(guestDAO.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Guest> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(guestDAO.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Guest> delete(@PathVariable Integer id) {
        guestDAO.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Guest> update(@RequestBody Guest guest) {
        guestDAO.update(guest);
        return ResponseEntity.ok(guestDAO.getById(guest.getId()));
        //WTF стоит ли делать запрос к БД для проверки?
        // это создает доп. нагрузку на БД. По идее, если не выскочило эксепшенов, то обновление прошло успешно и можно
        // вернуть значение параметра guest
    }
}
