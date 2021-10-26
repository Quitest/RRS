package ru.pel.rrs.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.pel.rrs.entities.Room;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class RoomDAO implements DAOInterface<Room, Long> {
    //WTF по идее соединение надо закрывать или возвращать в пул, если он есть.
    private Connection connection;

//    @Autowired
//    @Qualifier("messageSource")
//    private ReloadableResourceBundleMessageSource validationsMessageSource;

    @Autowired
//    @Qualifier("exceptionsMessageSource")
    private ReloadableResourceBundleMessageSource exceptionsMessageSource;

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
            statement.setLong(1, room.getId());
            statement.setString(2, room.getClassOfAccommodations());
            var newRows = statement.executeUpdate();
            if (newRows == 0) {
                throw new SQLException(
                        exceptionsMessageSource.getMessage("creating.is.failed.database.rows.was.not.modifed", null, LocaleContextHolder.getLocale()));

            }
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException(
                            exceptionsMessageSource.getMessage("room.creating.failed", null, LocaleContextHolder.getLocale()));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room.getId();
    }

    @Override
    public void delete(Long id) {
        try {
            var statement = connection.prepareStatement("DELETE FROM rooms WHERE id = ?");
            statement.setLong(1, id);
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
                room.setId(resultSet.getLong("id"));
                room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room getById(Long id) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE id=?";
        var statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        var resultSet = statement.executeQuery();
        Room room = new Room();
        if (resultSet.next()) {
            room.setId(resultSet.getLong("id"));
            room.setClassOfAccommodations(resultSet.getString("class_of_accommodations"));
        }
        if (room.isEmpty()) {
            throw new NoSuchElementException(
                    exceptionsMessageSource.getMessage("room.not.found.by.id", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        return room;
    }

    @Override
    public long update(Room room) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE rooms SET class_of_accommodations=? WHERE id=?");

        statement.setString(1, room.getClassOfAccommodations());
        statement.setLong(2, room.getId());
        statement.executeUpdate();
        return room.getId();
    }
}
