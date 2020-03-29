package de.kruebeck.dirk.demo.liquibase.persistency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "temporals")
@Getter
@Setter
@NoArgsConstructor
public class TemporalEntity {

    @Id
    private UUID id;

    // pk, temporal validity, audit
    private Instant validFrom;
    private Instant validTo;
    private Instant createdAt;
    private Instant modifiedAt;
    private String name;

    // payload / data
    private String text;

    @Column(name = "double_value")
    private Double longNamedDoubleValue;

    //private int mandatoryInt;

    public TemporalEntity(UUID id) {
        this.id = id;
    }

    @PreUpdate
    private void preUpdate() {
        modifiedAt = Instant.now();
    }

    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();
        createdAt = now;
        modifiedAt = now;
    }


}
