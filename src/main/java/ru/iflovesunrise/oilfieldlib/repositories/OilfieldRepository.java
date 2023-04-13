package ru.iflovesunrise.oilfieldlib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iflovesunrise.oilfieldlib.model.Oilfield;

import java.util.Optional;

@Repository
public interface OilfieldRepository extends JpaRepository<Oilfield, Integer> {

    Optional<Oilfield> findByName(String name);
}
