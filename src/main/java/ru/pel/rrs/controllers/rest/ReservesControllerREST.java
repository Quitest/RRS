package ru.pel.rrs.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.DAO.ReserveDAO;
import ru.pel.rrs.models.Reserve;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/reserves")
public class ReservesControllerREST implements RESTController<Reserve, Long> {
    @Autowired
    ReserveDAO reserveDAO;

    @PostMapping
    public ResponseEntity<Reserve> create(@Valid @RequestBody Reserve reserve) throws SQLException {
        reserveDAO.create(reserve);
        return ResponseEntity.ok(reserve);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reserve> delete(@PathVariable Long id) {
        reserveDAO.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserve>> getAll() {
        return ResponseEntity.ok(reserveDAO.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Reserve> getById(@PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(reserveDAO.getById(id));
    }

    @PutMapping
    public ResponseEntity<Reserve> update(@RequestBody Reserve reserve) throws SQLException {
        reserveDAO.update(reserve);
        return ResponseEntity.ok(reserve);
    }
}
