package ru.pel.rrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pel.rrs.models.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
