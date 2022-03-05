package org.esgi.use_cases.regulation.exposition.request;

import javax.validation.constraints.NotNull;

public final class RetrieveRegulationByIdRequest {

    @NotNull
    public int id;
}
