package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.pel.rrs.entities.Room;
import ru.pel.rrs.repositories.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReloadableResourceBundleMessageSource exceptionsMessageSource;


    public Room save(Room room){
        return roomRepository.save(room);
    }

    public void delete(long id){
        roomRepository.deleteById(id);
    }

    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    public Room getById(long id){
        Room room = roomRepository.findById(id).orElseThrow();
//        Room room = roomRepository.getById(id);
//        if (room == null || room.getId() == 0) {
//            throw new NoSuchElementException("Комнаты с таким ID не найдено");
//        }
        if (room.isEmpty()) {
            throw new NoSuchElementException(
                    exceptionsMessageSource.getMessage("room.not.found.by.id", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        return room;
    }
}
