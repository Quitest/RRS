package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pel.rrs.models.Guest;
import ru.pel.rrs.models.Reserve;
import ru.pel.rrs.models.Room;
import ru.pel.rrs.repositories.GuestRepository;
import ru.pel.rrs.repositories.ReserveRepository;
import ru.pel.rrs.repositories.RoomRepository;

import java.util.List;

@Service
public class ReserveService {
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    GuestRepository guestRepository;

    public void delete(long id){
        reserveRepository.deleteById(id);
    }

    public List<Reserve> getAll(){
        return reserveRepository.findAll();
    }

    public Reserve getById(long id){
        return reserveRepository.findById(id).orElseThrow();
    }

    public Reserve save(Reserve reserve){
        return reserveRepository.save(reserve);
    }

    public List<Room> getRoomsList(){
        return roomRepository.findAll();
    }

    public List<Guest> getGuestsList(){
        return guestRepository.findAll();
    }
}
