package org.esgi.use_cases.regulation.application.query;

import org.esgi.kernel.QueryHandler;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.domain.Regulations;

import java.util.List;

public final class RetrieveRegulationsQueryHandler implements QueryHandler<RetrieveRegulationsQuery, List<Regulation>> {

    Regulations regulations;

    public RetrieveRegulationsQueryHandler(Regulations regulations) {
        this.regulations = regulations;
    }

    @Override
    public List<Regulation> handle(RetrieveRegulationsQuery query) {
        return this.regulations.findAll();
    }
}
