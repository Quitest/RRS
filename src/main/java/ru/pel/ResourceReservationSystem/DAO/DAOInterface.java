package ru.pel.ResourceReservationSystem.DAO;

import java.util.List;

public interface DAOInterface<E, K> {

    void create(E entry);

    void delete(K id);

    List<E> getAll();

    E getById(K id);

    void update(E entry);
}
