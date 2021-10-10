package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Guest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class GuestDAO implements DAOInterface<Guest, Integer> {
    private Connection connection;

    public GuestDAO(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long create(Guest guest) {
        try {
            var statement = connection
                    .prepareStatement("INSERT INTO guests (lastname, name, middle_name, age) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, guest.getLastname());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getMiddleName());
            statement.setInt(4, guest.getAge());
            var newRows = statement.executeUpdate();
            if (newRows == 0) {
                throw new SQLException("Создать гостя не удалось, измененных строк БД нет");
            }
            guest.setId((int) getGeneratedId(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest.getId();
    }

    @Override
    public void delete(Integer id) {
        try {
            var statement = connection.prepareStatement("DELETE FROM guests WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Guest> getAll() {
        List<Guest> guestList = new ArrayList<>();
        try {
            var statement = connection.prepareStatement("SELECT * FROM guests");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guest.setMiddleName(resultSet.getString("middle_name"));
                guest.setLastname(resultSet.getString("lastname"));
                guest.setAge(resultSet.getInt("age"));
                guestList.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guestList;
    }

    @Override
    public Guest getById(Integer id) throws SQLException {
//        Guest guest = null;
//        try {
        var statement = connection.prepareStatement("SELECT * from guests WHERE id=?");
        statement.setInt(1, id);
        Guest guest = new Guest();
        var rs = statement.executeQuery();
        if (rs.next()) {
            guest.setId(rs.getInt("id"));
            guest.setLastname(rs.getString("lastname"));
            guest.setName(rs.getString("name"));
            guest.setMiddleName(rs.getString("middle_name"));
            guest.setAge(rs.getInt("age"));
        }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        if (guest.isEmpty()) {
            throw new NoSuchElementException("Гость " + id + " не существует");
        }
        return guest;
    }

    private long getGeneratedId(Statement statement) throws SQLException {
        try (var generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong("id");
            } else {
                throw new SQLException("Создать гостя не удалось, ID гостя не удалось получить.");
            }
        }
    }

    @Override
    public long update(Guest guest) throws SQLException {
//        try {
            var statement = connection
                    .prepareStatement("UPDATE guests SET lastname=?, name=?, middle_name=?, age=? WHERE id=?");
            statement.setString(1, guest.getLastname());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getMiddleName());
            statement.setInt(4, guest.getAge());
            statement.setInt(5, guest.getId());
            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return guest.getId();
    }
}
