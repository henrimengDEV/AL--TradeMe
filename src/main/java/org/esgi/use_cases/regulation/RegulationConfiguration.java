package org.esgi.use_cases.regulation;

import org.esgi.kernel.*;
import org.esgi.use_cases.regulation.application.command.CreateRegulationCommand;
import org.esgi.use_cases.regulation.application.command.CreateRegulationCommandHandler;
import org.esgi.use_cases.regulation.application.query.RetrieveRegulationByIdQuery;
import org.esgi.use_cases.regulation.application.query.RetrieveRegulationByIdQueryHandler;
import org.esgi.use_cases.regulation.application.query.RetrieveRegulationsQuery;
import org.esgi.use_cases.regulation.application.query.RetrieveRegulationsQueryHandler;
import org.esgi.use_cases.regulation.domain.Regulations;
import org.esgi.use_cases.regulation.infrastructure.InMemoryRegulations;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
public final class RegulationConfiguration {

    @Dependent
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> dataMap = new HashMap<>();
        dataMap.put(CreateRegulationCommand.class, new CreateRegulationCommandHandler(regulations()));
        return new SimpleCommandBus(dataMap);
    }

    @Dependent
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> dataMap = new HashMap<>();
        dataMap.put(RetrieveRegulationsQuery.class, new RetrieveRegulationsQueryHandler(regulations()));
        dataMap.put(RetrieveRegulationByIdQuery.class, new RetrieveRegulationByIdQueryHandler(regulations()));
        return new SimpleQueryBus(dataMap);
    }

    @Singleton
    public Regulations regulations() {
        return InMemoryRegulations.getInstance();
    }
}
