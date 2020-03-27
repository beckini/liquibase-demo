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

    public TemporalEntity(UUID id) {
        this.id = id;
    }

    // pk, temporal validity, audit

    @Id
    private UUID id;

    private Instant validFrom;

    private Instant validTo;

    private Instant createdAt;

    private Instant modifiedAt;

    // payload / data

    private String name;

    private String text;

    @Column(name = "double_value")
    private Double longNamedDoubleValue;

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
