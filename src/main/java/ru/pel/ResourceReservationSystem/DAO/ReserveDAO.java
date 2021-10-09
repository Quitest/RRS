// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Reserve;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ReserveDAO implements DAOInterface<Reserve, Integer> {
    private Connection connection;

    public ReserveDAO(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Reserve createdReserve) {
        try {
            var statement = connection.prepareStatement("INSERT INTO reserves " +
                    "(check_in,check_out,room_id,guest_id) VALUES (?, ?, ?, ?)");
            statement.setTimestamp(1, Timestamp.valueOf(createdReserve.getCheckIn()));
            statement.setTimestamp(2, Timestamp.valueOf(createdReserve.getCheckOut()));
            statement.setInt(3, createdReserve.getRoomId());
            statement.setInt(4, createdReserve.getGuestId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idForDelete) {
        try {
            var statement = connection.prepareStatement("DELETE FROM reserves WHERE id=?");
            statement.setInt(1, idForDelete);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reserve> getAll() {
        List<Reserve> reserveList = new ArrayList<>();
        try {
            var statement = connection.prepareStatement("SELECT * FROM reserves");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reserve reserve = new Reserve();
                reserve.setId(resultSet.getInt("id"));
                reserve.setCheckIn(resultSet.getTimestamp("check_in").toLocalDateTime());
                reserve.setCheckOut(resultSet.getTimestamp("check_out").toLocalDateTime());
                reserve.setGuestId(resultSet.getInt("guest_id"));
                reserve.setRoomId(resultSet.getInt("room_id"));
                reserveList.add(reserve);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserveList;
    }

    @Override
    public Reserve getById(Integer id) throws SQLException {
        Reserve reserveListrve = null;
//        try {
        var statement = connection.prepareStatement("SELECT * FROM reserves WHERE id=?");
        statement.setInt(1, id);
        var rs = statement.executeQuery();
        Reserve reserve = new Reserve();
        if (rs.next()) {
            reserve.setId(rs.getInt("id"));
            reserve.setGuestId(rs.getInt("guest_id"));
            reserve.setRoomId(rs.getInt("room_id"));
            reserve.setCheckIn(rs.getTimestamp("check_in").toLocalDateTime());
            reserve.setCheckOut(rs.getTimestamp("check_out").toLocalDateTime());
        }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        if (reserve.isEmpty()) {
            throw new NoSuchElementException("Бронь № " + id + " не существует");
        }
        return reserve;
    }

    @Override
    public void update(Reserve entry) {
//        throw new UnsupportedOperationException("Операция update не реализована");
        try {
            var statement = connection
                    .prepareStatement("UPDATE reserves SET check_in=?, check_out=?, room_id=?, guest_id=? WHERE id=?");
            statement.setTimestamp(1, Timestamp.valueOf(entry.getCheckIn()));
            statement.setTimestamp(2, Timestamp.valueOf(entry.getCheckOut()));
            statement.setInt(3, entry.getRoomId());
            statement.setInt(4, entry.getGuestId());
            statement.setInt(5, entry.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
