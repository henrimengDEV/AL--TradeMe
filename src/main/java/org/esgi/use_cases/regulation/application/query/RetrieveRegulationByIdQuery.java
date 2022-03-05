package org.esgi.use_cases.regulation.application.query;

import org.esgi.kernel.Query;

public final class RetrieveRegulationByIdQuery implements Query {

    public int id;

    public RetrieveRegulationByIdQuery(int id) {
        this.id = id;
    }
}
