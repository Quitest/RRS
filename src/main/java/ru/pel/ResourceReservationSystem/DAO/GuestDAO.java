package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Guest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void create(Guest entry) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
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
    public Guest getById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Guest entry) {
        throw new UnsupportedOperationException();
    }
}
