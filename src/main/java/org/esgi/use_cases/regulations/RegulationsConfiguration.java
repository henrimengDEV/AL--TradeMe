package org.esgi.use_cases.regulations;

import org.esgi.ApplicationConfiguration;
import org.esgi.shared_kernel.cqs.*;
import org.esgi.use_cases.regulations.application.command.RegulateUnsubscribedTradesman;
import org.esgi.use_cases.regulations.application.command.RegulateUnsubscribedTradesmanHandler;
import org.esgi.use_cases.regulations.domain.RegulationsRepository;
import org.esgi.use_cases.regulations.infrastructure.InMemoryRegulationsRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class RegulationsConfiguration {

    private final ApplicationConfiguration appConfiguration;

    public RegulationsConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    //Command bus
    @Singleton
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(RegulateUnsubscribedTradesman.class, new RegulateUnsubscribedTradesmanHandler(regulationsRepository(), appConfiguration.domainEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @Singleton
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Repository
    private RegulationsRepository regulationsRepository() {
        return  InMemoryRegulationsRepository.getInstance();
    }

}
