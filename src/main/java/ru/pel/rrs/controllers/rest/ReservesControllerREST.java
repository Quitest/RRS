package ru.pel.rrs.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.entities.Reserve;
import ru.pel.rrs.services.ReserveService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/reserves")
public class ReservesControllerREST implements RESTController<Reserve, Long> {
//    @Autowired
//    ReserveDAO reserveDAO;

    @Autowired
    ReserveService reserveService;

    @PostMapping
    //TODO почитать про работу моей проверки https://stackoverflow.com/questions/52460568/could-not-commit-jpa-transaction-nested-exception-is-javax-persistence-rollback
    public ResponseEntity<Reserve> create(@Valid @RequestBody Reserve reserve) throws SQLException {
//        reserveDAO.create(reserve);
        reserveService.save(reserve);
        return ResponseEntity.ok(reserve);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reserve> delete(@PathVariable Long id) {
//        reserveDAO.delete(id);
        reserveService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserve>> getAll() {
//        return ResponseEntity.ok(reserveDAO.getAll());
        return ResponseEntity.ok(reserveService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Reserve> getById(@PathVariable Long id) throws SQLException {
//        return ResponseEntity.ok(reserveDAO.getById(id));
        return ResponseEntity.ok(reserveService.getById(id));
    }

    @PutMapping
    public ResponseEntity<Reserve> update(@RequestBody Reserve reserve) throws SQLException {
//        reserveDAO.update(reserve);
//        return ResponseEntity.ok(reserve);
        return ResponseEntity.ok(reserveService.save(reserve));
    }
}
