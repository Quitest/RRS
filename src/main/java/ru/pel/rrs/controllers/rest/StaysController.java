package ru.pel.rrs.controllers.rest;

//import org.modelmapper.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.dto.StaysDTO;
import ru.pel.rrs.entities.bulders.StaysBuilder;
import ru.pel.rrs.entities.bulders.StaysDirector;
import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.entities.stays.features.Facility;
import ru.pel.rrs.services.StaysService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/stays")
public class StaysController /*implements RESTController<Stays,Long>*/ {
    @Autowired
    private StaysService staysService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/builder")
    public ResponseEntity<Stays> buildTypicalStays(@RequestBody(required = false) String type) {
        StaysBuilder builder = new StaysBuilder();
        StaysDirector.buildHouse(builder);
        Stays house = builder.getStays();
//        StaysDirector.buildHouse(builder);
        Stays housePersisted = staysService.save(house);
        return ResponseEntity.ok(housePersisted);
    }

    private StaysDTO convertToDto(Stays stays) {
        return modelMapper.map(stays, StaysDTO.class);
    }

    //Источник https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
    private Stays convertToEntity(StaysDTO staysDTO) {
        return modelMapper.map(staysDTO, Stays.class);
    }

    @PostMapping
    public ResponseEntity<StaysDTO> create(@RequestBody StaysDTO staysDTO) {
        //WTF надо ли делать столько конвертаций DTO->Entity->DTO?
        StaysDTO saved = convertToDto(staysService.save(convertToEntity(staysDTO)));
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping
    public ResponseEntity<Stays> delete(Long id) {
        staysService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StaysDTO>> getAll() {
        List<StaysDTO> staysDTOS = staysService.getAll().stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(staysDTOS);
//        return ResponseEntity.ok(staysService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stays> getById(@PathVariable Long id) {
        return ResponseEntity.ok(staysService.getById(id));
    }

    @PutMapping
    public ResponseEntity<Stays> update(@RequestBody Stays entity) {
        return ResponseEntity.ok(staysService.save(entity));
    }

    @GetMapping("/find")
    public  ResponseEntity<List<StaysDTO>> findStays(@RequestBody(required = false) Set<String> propertyType){
//        Set<String> f = Set.of(propertyType);
        List<Stays> byFacilities = staysService.findByFacilities(propertyType);
        List<StaysDTO> staysDTOS = new ArrayList<>();
        for (Stays s : byFacilities){
            staysDTOS.add(convertToDto(s));
        }

        return ResponseEntity.ok(staysDTOS);
    }
}
