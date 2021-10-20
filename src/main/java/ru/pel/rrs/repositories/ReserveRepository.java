package ru.pel.rrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pel.rrs.models.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
