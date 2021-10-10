// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Reserve;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    public long create(Reserve reserve) throws SQLException {
        isReserved(reserve.getRoomId(), reserve.getCheckIn(), reserve.getCheckOut());
        var statement = connection.prepareStatement("INSERT INTO reserves " +
                "(check_in,check_out,room_id,guest_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setTimestamp(1, Timestamp.valueOf(reserve.getCheckIn()));
        statement.setTimestamp(2, Timestamp.valueOf(reserve.getCheckOut()));
        statement.setInt(3, reserve.getRoomId());
        statement.setInt(4, reserve.getGuestId());
        var affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Создать бронь не удалось, нет измененных строк");
        }
        reserve.setId((int) getGeneratedId(statement));
        return reserve.getRoomId();
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
        if (reserve.isEmpty()) {
            throw new NoSuchElementException("Бронь № " + id + " не существует");
        }
        return reserve;
    }

    private long getGeneratedId(Statement statement) throws SQLException {
        try (var generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong("id");
            } else {
                throw new SQLException("Создать бронь не удалось, ID брони не удалось получить.");
            }
        }
    }

    /**
     * Проверка номера на занятость номера в указанный период.
     *
     * @param roomId   номер бронируемой комнаты
     * @param checkIn  дата и время начала брони
     * @param checkOut дата и время окончания брони
     * @return ID комнаты, если временной период запрашиваемой брони не пересекается с временным периодом существующей брони.
     * @throws IllegalArgumentException если комната занята в запрашиваемый период.
     * @throws SQLException             при ошибках работы SQL.
     */
    public long isReserved(long roomId, LocalDateTime checkIn, LocalDateTime checkOut) throws SQLException {
        var statement = connection.prepareStatement("SELECT check_in, check_out FROM reserves WHERE room_id=?");
        statement.setLong(1, roomId);
        var resultSet = statement.executeQuery();
        while (resultSet.next()) {
            LocalDateTime reservedCheckIn = resultSet.getTimestamp("check_in").toLocalDateTime();
            LocalDateTime reservedCheckOut = resultSet.getTimestamp("check_out").toLocalDateTime();
            if (checkIn.isBefore(reservedCheckOut) && checkOut.isAfter(reservedCheckIn)) {
                String msg = "Не возможно забронировать комнату " +
                        roomId +
                        " на период с " +
                        checkIn +
                        " по " +
                        checkOut;
                throw new IllegalArgumentException(msg);
            }
        }
        return roomId;
    }

    @Override
    public long update(Reserve reserve) throws SQLException {
        isReserved(reserve.getRoomId(), reserve.getCheckIn(), reserve.getCheckOut());

        var statement = connection
                .prepareStatement("UPDATE reserves SET check_in=?, check_out=?, room_id=?, guest_id=? WHERE id=?");
        statement.setTimestamp(1, Timestamp.valueOf(reserve.getCheckIn()));
        statement.setTimestamp(2, Timestamp.valueOf(reserve.getCheckOut()));
        statement.setInt(3, reserve.getRoomId());
        statement.setInt(4, reserve.getGuestId());
        statement.setInt(5, reserve.getId());
        statement.executeUpdate();
        return reserve.getRoomId();
    }
}
