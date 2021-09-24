package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDAO {
    private static int ID = -1;
    private List<Room> roomList;

    {
        roomList = new ArrayList<>();
        roomList.add(new Room(++ID, LocalDateTime.now(), "Room title #" + ID));
        roomList.add(new Room(++ID, LocalDateTime.now(), "Room title #" + ID));
        roomList.add(new Room(++ID, LocalDateTime.now(), "Room title #" + ID));
        roomList.add(new Room(++ID, LocalDateTime.now(), "Room title #" + ID));
        roomList.add(new Room(++ID, LocalDateTime.now(), "Room title #" + ID));
    }

    public boolean delete(int id) {
//        return roomList.remove(id);
        return roomList.removeIf(r->r.getId() == id);
    }

    public List<Room> getAllRooms() {
        return roomList;
    }

    public Room getRoomById(int id) {
        return roomList.stream()
                .filter(res -> id == res.getId())
                .findFirst().orElse(null);
    }

    public void save(Room room) {
        room.setId(++ID);
        roomList.add(room);
    }

    public void update(int id, Room editedRoom) {
        Room roomToBeUpdated = getRoomById(id);
        roomToBeUpdated.setEventDateTime(editedRoom.getEventDateTime());
        roomToBeUpdated.setTitle(editedRoom.getTitle());
    }
}
