package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/rrs_db";
    private static final String USERNAME = "rrs_user";
    private static final String PASSWORD = "toor";
    private static Connection connection;

    static {
        try {
//            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            var statement = connection.prepareStatement("DELETE FROM rooms WHERE id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rooms");

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
                room.setCheckIn(resultSet.getTimestamp("check_in").toLocalDateTime());
                room.setCheckOut(resultSet.getTimestamp("check_out").toLocalDateTime());
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomById(int id) {
        Room room = new Room();
        try {
            String sql = "SELECT * FROM rooms WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                room.setId(resultSet.getInt("id"));
                room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
                room.setCheckIn(resultSet.getTimestamp("check_in").toLocalDateTime());
                room.setCheckOut(resultSet.getTimestamp("check_out").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
    
    public void save(Room room) {
        try {
            var statement = connection.prepareStatement("INSERT INTO rooms VALUES (?,?,?,?,?)");
            statement.setInt(1,room.getId());
            statement.setString(2, room.getClassOfAccommodations());
            statement.setTimestamp(3, Timestamp.valueOf(room.getCheckIn()));
            statement.setTimestamp(4, Timestamp.valueOf(room.getCheckOut()));
            statement.setInt(5, 0); // FIXME: 26.09.2021 вставлять ID реального гостя
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void update(int id, Room editedRoom) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE rooms SET class_of_accommodations=?, check_in=?, check_out=?, guest_id=? WHERE id=?");
            statement.setString(1, editedRoom.getClassOfAccommodations());
            statement.setTimestamp(2, Timestamp.valueOf(editedRoom.getCheckIn()));
            statement.setTimestamp(3, Timestamp.valueOf(editedRoom.getCheckOut()));
            statement.setInt(4, 1); // FIXME: 26.09.2021 Вводить реальный ID гостя
            statement.setInt(5,id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
