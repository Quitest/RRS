package ru.pel.rrs.repositories;

import ru.pel.rrs.entities.stays.Stays;

import java.util.List;
import java.util.Set;

public interface StaysFinderRepository {
    List<Stays> findByFacilities(Set<String> facilities);
}
