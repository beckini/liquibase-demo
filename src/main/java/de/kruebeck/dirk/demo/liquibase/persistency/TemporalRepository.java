package de.kruebeck.dirk.demo.liquibase.persistency;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TemporalRepository extends CrudRepository<TemporalEntity, UUID> {

    // Variante 1

    List<TemporalEntity> findByNameAndValidFromBeforeAndValidToAfter(String name, Instant lookupDate, Instant lookupDateRepeated);

    default List<TemporalEntity> findByNameAndValidity(String name, Instant validDate) {
        return findByNameAndValidFromBeforeAndValidToAfter(name, validDate, validDate);
    }

    // Variante 2

    @Query("select te from TemporalEntity te where te.name = :name and te.validFrom < :validDate and te.validTo > :validDate")
    List<TemporalEntity> findByNameAndValidityVar2(@Param("name") String name, @Param("validDate") Instant validDate);

}
