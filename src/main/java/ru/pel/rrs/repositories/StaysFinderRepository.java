package ru.pel.rrs.repositories;

import ru.pel.rrs.entities.stays.Stays;

import java.util.List;

public interface StaysFinderRepository {
    List<Stays> findByFacilities(String facilities);
}
