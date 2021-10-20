package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pel.rrs.models.Room;
import ru.pel.rrs.repositories.RoomsRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;

    public Room save(Room room){
        return roomsRepository.save(room);
    }

    public void delete(long id){
        roomsRepository.deleteById(id);
    }

    public List<Room> getAll(){
        return roomsRepository.findAll();
    }

    public Room getById(long id){
        Room room = roomsRepository.getById(id);
        if (room == null || room.getId() == 0) {
            throw new NoSuchElementException("Комнаты с таким ID не найдено");
        }
        return room;
    }
}
