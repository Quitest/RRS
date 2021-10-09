package ru.pel.ResourceReservationSystem.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.ReserveDAO;
import ru.pel.ResourceReservationSystem.models.Reserve;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/reserves")
public class ReservesControllerREST implements RESTController<Reserve> {
    @Autowired
    ReserveDAO reserveDAO;

    @PostMapping
    public ResponseEntity<Reserve> create(@RequestBody Reserve reserve) {
        reserveDAO.create(reserve);
        return ResponseEntity.ok(reserve);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reserve> delete(@PathVariable Integer id) {
        reserveDAO.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserve>> getAll() {
        return ResponseEntity.ok(reserveDAO.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Reserve> getById(@PathVariable Integer id) throws SQLException {
        return ResponseEntity.ok(reserveDAO.getById(id));
    }

    @PutMapping
    public ResponseEntity<Reserve> update(@RequestBody Reserve reserve) {
        reserveDAO.update(reserve);
        return ResponseEntity.ok(reserve);
    }
}
