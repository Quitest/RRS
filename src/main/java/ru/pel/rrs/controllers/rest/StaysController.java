package ru.pel.rrs.controllers.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.dto.StaysDTO;
import ru.pel.rrs.entities.stays.Builder;
import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.entities.stays.StaysBuilder;
import ru.pel.rrs.entities.stays.StaysDirector;
import ru.pel.rrs.services.StaysService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/stays")
public class StaysController /*implements RESTController<Stays,Long>*/ {
    @Autowired
    private StaysService staysService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Stays> create(@RequestBody Stays entity) throws SQLException {
        return ResponseEntity.ok(staysService.save(entity));
    }

    @PostMapping("/builder")
    public ResponseEntity<Stays> buildTypicalStays(@RequestBody(required = false) String type){
        StaysBuilder builder = new StaysBuilder();
        StaysDirector.buildHouse(builder);
        Stays house = builder.getStays();
//        StaysDirector.buildHouse(builder);
        Stays housePersisted = staysService.save(house);
        return ResponseEntity.ok(housePersisted);
    }

    @DeleteMapping
    public ResponseEntity<Stays> delete(Long id) {
        staysService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Stays>> getAll() {
//        List<StaysDTO> staysDTOS = staysService.getAll().stream()
//                .map(this::convertToDto)
//                .toList();
//        return ResponseEntity.ok(staysDTOS);
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

    private StaysDTO convertToDto(Stays stays){
        return modelMapper.map(stays, StaysDTO.class);
    }
//Источник https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
    private Stays convertToEntity(StaysDTO staysDTO){
        return modelMapper.map(staysDTO, Stays.class);
    }
}
