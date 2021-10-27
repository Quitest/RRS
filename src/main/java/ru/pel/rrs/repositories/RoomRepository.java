package ru.pel.rrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pel.rrs.entities.stays.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
