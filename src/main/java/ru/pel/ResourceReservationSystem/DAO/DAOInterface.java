package ru.pel.ResourceReservationSystem.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<E, K> {

    long create(E entry) throws SQLException;

    void delete(K id);

    List<E> getAll();

    E getById(K id) throws SQLException;

    long update(E entry) throws SQLException;
}
