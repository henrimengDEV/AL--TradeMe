package org.esgi.use_cases.regulation.application.query;

import org.esgi.kernel.QueryHandler;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.domain.RegulationId;
import org.esgi.use_cases.regulation.domain.Regulations;

import java.util.List;

public final class RetrieveRegulationByIdQueryHandler implements QueryHandler<RetrieveRegulationByIdQuery, Regulation> {

    Regulations regulations;

    public RetrieveRegulationByIdQueryHandler(Regulations regulations) {
        this.regulations = regulations;
    }

    @Override
    public Regulation handle(RetrieveRegulationByIdQuery query) {
        return this.regulations.findById(new RegulationId(query.id));
    }
}
