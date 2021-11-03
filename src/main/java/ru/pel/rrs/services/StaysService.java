package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.repositories.StaysRepository;

import java.util.List;

@Service
public class StaysService {

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
        stays.setRoomFacilitiesSet(stays.getRoomFacilitiesSet());
        stays.getRoomFacilitiesSet()
                .forEach(f->f.setStays(stays));

        return staysRepository.save(stays);
    }
}
