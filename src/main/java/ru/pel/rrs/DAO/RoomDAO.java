package ru.pel.rrs.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pel.rrs.models.Room;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public long create(Room room) {
        try {
            var statement = connection.prepareStatement("INSERT INTO rooms VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, room.getId());
            statement.setString(2, room.getClassOfAccommodations());
            var newRows = statement.executeUpdate();
            if (newRows == 0) {
                throw new SQLException("Создать комнату не удалось, в БД строки не изменялись");
            }
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException("Создать комнату не удалось");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room.getId();
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
    public Room getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE id=?";
        var statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        var resultSet = statement.executeQuery();
        Room room = new Room();
        if (resultSet.next()) {
            room.setId(resultSet.getInt("id"));
            room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
        }
        if (room.isEmpty()) {
            throw new NoSuchElementException("Комнаты " + id + " не существует");
        }
        return room;
    }

    @Override
    public long update(Room room) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE rooms SET class_of_accommodations=? WHERE id=?");

        statement.setString(1, room.getClassOfAccommodations());
        statement.setInt(2, room.getId());
        statement.executeUpdate();
        return room.getId();
    }
}
