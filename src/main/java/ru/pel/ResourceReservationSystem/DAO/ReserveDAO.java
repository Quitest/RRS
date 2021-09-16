package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Reserve;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReserveDAO {
    private static int ID = -1;
    private List<Reserve> reserveList;

    {
        reserveList = new ArrayList<>();
        reserveList.add(new Reserve(++ID, LocalDateTime.now(), "Reserve title #" + ID));
        reserveList.add(new Reserve(++ID, LocalDateTime.now(), "Reserve title #" + ID));
        reserveList.add(new Reserve(++ID, LocalDateTime.now(), "Reserve title #" + ID));
        reserveList.add(new Reserve(++ID, LocalDateTime.now(), "Reserve title #" + ID));
        reserveList.add(new Reserve(++ID, LocalDateTime.now(), "Reserve title #" + ID));
    }

    public Reserve delete(int id) {
        return reserveList.remove(id);
    }

    public List<Reserve> getAllReserves() {
        return reserveList;
    }

    public Reserve getReserveById(int id) {
        return reserveList.stream()
                .filter(res -> id == res.getId())
                .findFirst().orElse(null);
    }

    public void save(Reserve reserve) {
        reserve.setId(++ID);
        reserveList.add(reserve);
    }
}
