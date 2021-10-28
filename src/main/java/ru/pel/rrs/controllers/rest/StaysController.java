package ru.pel.rrs.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.services.StaysService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/stays")
public class StaysController implements RESTController<Stays,Long> {
    @Autowired
    private StaysService staysService;

    @PostMapping
    public ResponseEntity<Stays> create(@RequestBody Stays entity) throws SQLException {
        return ResponseEntity.ok(staysService.save(entity));
    }

    @DeleteMapping
    public ResponseEntity<Stays> delete(Long id) {
        staysService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Stays>> getAll() {
        return ResponseEntity.ok(staysService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Stays> getById(@PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(staysService.getById(id));
    }

    @PutMapping
    public ResponseEntity<Stays> update(@RequestBody Stays entity) throws SQLException {
        return ResponseEntity.ok(staysService.save(entity));
    }
}
