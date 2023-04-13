package ru.iflovesunrise.oilfieldlib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.iflovesunrise.oilfieldlib.model.OilWell;

@Repository
public interface OilWellRepository extends JpaRepository<OilWell, Integer> {
    @Query(value = "SELECT COUNT(debit) FROM oil_well WHERE oilfield_id = :oilfieldId", nativeQuery = true)
    long extentOfProductionByOilfieldId(int oilfieldId);
}
