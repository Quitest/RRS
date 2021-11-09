package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.repositories.StaysRepository;

import java.util.List;

@Service
public class StaysService implements ru.pel.rrs.services.Service {

    @Autowired
    private StaysRepository staysRepository;

    public void delete(long id) {
        staysRepository.deleteById(id);
    }

    public List<Stays> getAll() {
        return staysRepository.findAll();
    }

    public Stays getById(long id) {
        return staysRepository.findById(id).orElseThrow();
    }

    public Stays save(Stays stays) {
//        Stays savedStays = new Stays();
//
//        savedStays.setFacilities(stays.getFacilities());
//        savedStays.setFunThingsToDo(stays.getFunThingsToDo());
//        savedStays.setMeals(stays.getMeals());
//        savedStays.setPropertyType(stays.getPropertyType());
//        savedStays.setReserveSet(stays.getReserveSet());
//        savedStays.setRoomFacilitiesSet(stays.getRoomFacilitiesSet());
//        savedStays.setRoomNumber(stays.getRoomNumber());
//        savedStays.setNumber(stays.getNumber());

//        savedStays.getFacilities().forEach(facility -> facility.getStaysSet().add(stays));
//        savedStays
//        savedStays.getRoomFacilitiesSet().forEach(f->f.setStays(stays));
//
//        savedStays.setMeals(stays.getMeals());
//        savedStays.getMeals().forEach(m-> m.setStays(stays));

//        return staysRepository.save(savedStays);
        return staysRepository.save(stays);
    }

    public List<Stays> findByFacilities(String facilities){
        return staysRepository.findByFacilities(facilities);
    }

}
