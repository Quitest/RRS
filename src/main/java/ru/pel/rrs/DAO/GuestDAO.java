package ru.pel.rrs.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.pel.rrs.models.Guest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class GuestDAO implements DAOInterface<Guest, Long> {
    @Autowired
//            @Qualifier("exceptionsMessageSource")
    ReloadableResourceBundleMessageSource exceptionsMessageSource;
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
            var statement = connection.prepareStatement(
                    "INSERT INTO guests (lastname, name, middle_name, age) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, guest.getLastname());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getMiddleName());
            statement.setInt(4, guest.getAge());
            var newRows = statement.executeUpdate();
            if (newRows == 0) {
                throw new SQLException(exceptionsMessageSource.getMessage("creating.is.failed.database.rows.was.not.modified",
                                null,
                                getLocale())
                );
            }
            guest.setId(getGeneratedId(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest.getId();
    }

    @Override
    public void delete(Long id) {
        try {
            var statement = connection.prepareStatement("DELETE FROM guests WHERE id=?");
            statement.setLong(1, id);
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
                guest.setId(resultSet.getLong("id"));
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
    public Guest getById(Long id) throws SQLException {
        var statement = connection.prepareStatement("SELECT * from guests WHERE id=?");
        statement.setLong(1, id);
        Guest guest = new Guest();
        var rs = statement.executeQuery();
        if (rs.next()) {
            guest.setId(rs.getLong("id"));
            guest.setLastname(rs.getString("lastname"));
            guest.setName(rs.getString("name"));
            guest.setMiddleName(rs.getString("middle_name"));
            guest.setAge(rs.getInt("age"));
        }
        if (guest.isEmpty()) {
            throw new NoSuchElementException(exceptionsMessageSource.getMessage("id.does.not.exist",
                    new Object[]{id},
                    getLocale()));
        }
        return guest;
    }

    private long getGeneratedId(Statement statement) throws SQLException {
        try (var generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong("id");
            } else {
                throw new SQLException(exceptionsMessageSource.getMessage("object.creation.failed.id.not.received",
                        null,
                        getLocale()));
            }
        }
    }

    @Override
    public long update(Guest guest) throws SQLException {
        var statement = connection
                .prepareStatement("UPDATE guests SET lastname=?, name=?, middle_name=?, age=? WHERE id=?");
        statement.setString(1, guest.getLastname());
        statement.setString(2, guest.getName());
        statement.setString(3, guest.getMiddleName());
        statement.setInt(4, guest.getAge());
        statement.setLong(5, guest.getId());
        statement.executeUpdate();
        return guest.getId();
    }
}
