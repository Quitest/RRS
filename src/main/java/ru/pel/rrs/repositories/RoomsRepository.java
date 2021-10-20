package ru.pel.rrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pel.rrs.models.Room;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Room,Long> {

//    Room findById(long id);

}
