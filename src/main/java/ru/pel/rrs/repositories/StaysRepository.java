package ru.pel.rrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pel.rrs.entities.stays.Stays;

@Repository
public interface StaysRepository extends JpaRepository<Stays, Long>, StaysFinderRepository {


}