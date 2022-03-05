package org.esgi.use_cases.regulation.domain;

import org.esgi.kernel.AggregateRoot;

import java.time.LocalDate;

@AggregateRoot
public final class Regulation {

    private final RegulationId id;
    private final LocalDate creationDate;

    private Regulation(RegulationId id, LocalDate creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public static Regulation ofNow(RegulationId id) {
        return new Regulation(id, LocalDate.now());
    }

    public RegulationId getId() {
        return id;
    }
}
