package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Room;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDAO implements DAOInterface<Room, Integer> {
    //WTF по идее соединение надо закрывать или возвращать в пул, если он есть.
    private static Connection connection;

    @Autowired
    public RoomDAO(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void create(Room room) {
        try {
            var statement = connection.prepareStatement("INSERT INTO rooms VALUES (?,?)");
            statement.setInt(1, room.getId());
            statement.setString(2, room.getClassOfAccommodations());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            var statement = connection.prepareStatement("DELETE FROM rooms WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rooms ORDER BY id");

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room getById(Integer id) {
        Room room = null;
        try {
            String sql = "SELECT * FROM rooms WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            var resultSet = statement.executeQuery();
            room = new Room();
            while (resultSet.next()) {
                room.setId(resultSet.getInt("id"));
                room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void update(Room editedRoom) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE rooms SET class_of_accommodations=? WHERE id=?");

            statement.setString(1, editedRoom.getClassOfAccommodations());
            statement.setInt(2, editedRoom.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
