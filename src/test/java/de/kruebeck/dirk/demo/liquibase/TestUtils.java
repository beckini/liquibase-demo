package de.kruebeck.dirk.demo.liquibase;

import de.kruebeck.dirk.demo.liquibase.persistency.TemporalEntity;
import de.kruebeck.dirk.demo.liquibase.persistency.TemporalRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestUtils {

    public static final Random R = new Random();

    // must be called within transaction context
    public static List<TemporalEntity> createAndSaveRandomEntities(TemporalRepository repository, String namePrefix, int amount) {
        List<TemporalEntity> result = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            TemporalEntity entity = createRandomEntity(namePrefix + "-" + i);
            result.add(repository.save(entity));
        }

        return result;
    }


    public static TemporalEntity createRandomEntity(String name) {
        TemporalEntity entity = new TemporalEntity();

        entity.setId(UUID.randomUUID());

        entity.setValidFrom(Instant.now().minus(1 + R.nextInt(10), ChronoUnit.DAYS));
        entity.setValidTo(Instant.now().plus(1 + R.nextInt(10), ChronoUnit.DAYS));

        entity.setName(name);

        return entity;
    }
}
