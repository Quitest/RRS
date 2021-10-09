// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Reserve;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
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
    public void create(Reserve reserve) {
        try {
            if(isReserved(reserve.getRoomId(), reserve.getCheckIn(),reserve.getCheckOut())){
                String msg = "Не возможно забронировать комнату " +
                        reserve.getRoomId() +
                        " на период с " +
                        reserve.getCheckIn() +
                        " по " +
                        reserve.getCheckOut();
                throw new IllegalArgumentException(msg);
            }
            var statement = connection.prepareStatement("INSERT INTO reserves " +
                    "(check_in,check_out,room_id,guest_id) VALUES (?, ?, ?, ?)");
            statement.setTimestamp(1, Timestamp.valueOf(reserve.getCheckIn()));
            statement.setTimestamp(2, Timestamp.valueOf(reserve.getCheckOut()));
            statement.setInt(3, reserve.getRoomId());
            statement.setInt(4, reserve.getGuestId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка номера на занятость номера в указанный период.
     * @param roomId
     * @param checkIn
     * @param checkOut
     * @return true - если временной период запрашиваемой брони пересекается с временным периодом существующей брони.
     * false - в противном случае.
     */
    public boolean isReserved(long roomId, LocalDateTime checkIn, LocalDateTime checkOut) throws SQLException {
        var statement = connection.prepareStatement("SELECT check_in, check_out FROM reserves WHERE room_id=?");
        statement.setLong(1,roomId);
        var resultSet = statement.executeQuery();
        while (resultSet.next()){
            LocalDateTime reservedCheckIn = resultSet.getTimestamp("check_in").toLocalDateTime();
            LocalDateTime reservedCheckOut = resultSet.getTimestamp("check_out").toLocalDateTime();
            if (checkIn.isBefore(reservedCheckOut) && checkOut.isAfter(reservedCheckIn)){
                return true;
            }
        }

        return false;
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
    public void update(Reserve reserve) {
//        throw new UnsupportedOperationException("Операция update не реализована");
        try {
            if(isReserved(reserve.getRoomId(), reserve.getCheckIn(),reserve.getCheckOut())){
                String msg = "Не возможно забронировать комнату " +
                        reserve.getRoomId() +
                        " на период с " +
                        reserve.getCheckIn() +
                        " по " +
                        reserve.getCheckOut();
                throw new IllegalArgumentException(msg);
            }

            var statement = connection
                    .prepareStatement("UPDATE reserves SET check_in=?, check_out=?, room_id=?, guest_id=? WHERE id=?");
            statement.setTimestamp(1, Timestamp.valueOf(reserve.getCheckIn()));
            statement.setTimestamp(2, Timestamp.valueOf(reserve.getCheckOut()));
            statement.setInt(3, reserve.getRoomId());
            statement.setInt(4, reserve.getGuestId());
            statement.setInt(5, reserve.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
