package de.kruebeck.dirk.demo.liquibase.persistency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static de.kruebeck.dirk.demo.liquibase.TestUtils.createAndSaveRandomEntities;

@SpringBootTest()
@ActiveProfiles("it")
public class TemporalRepositoryManualTestIT {

    @Autowired
    protected TemporalRepository temporalRepository;

    @Test
    public void leaveDataInDBTest() throws Exception {
        createAndSaveRandomEntities(temporalRepository, "Leave in postgres DB", 10);
    }

}
