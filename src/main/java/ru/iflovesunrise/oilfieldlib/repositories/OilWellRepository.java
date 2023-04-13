package ru.iflovesunrise.oilfieldlib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iflovesunrise.oilfieldlib.model.OilWell;

@Repository
public interface OilWellRepository extends JpaRepository<OilWell, Integer> {
}
