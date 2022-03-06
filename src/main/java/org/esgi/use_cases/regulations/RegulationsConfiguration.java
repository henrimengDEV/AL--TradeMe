package org.esgi.use_cases.regulations;

import org.esgi.ApplicationConfiguration;
import org.esgi.shared_kernel.annotations.Configuration;
import org.esgi.shared_kernel.cqs.*;
import org.esgi.use_cases.regulations.application.command.CreateMemberRegulation;
import org.esgi.use_cases.regulations.application.command.CreateMemberRegulationHandler;
import org.esgi.use_cases.regulations.application.command.RegulateUnsubscribedTradesman;
import org.esgi.use_cases.regulations.application.command.RegulateUnsubscribedTradesmanHandler;
import org.esgi.use_cases.regulations.domain.RegulationsRepository;
import org.esgi.use_cases.regulations.infrastructure.InMemoryRegulationsRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class RegulationsConfiguration {
    private final ApplicationConfiguration appConfiguration;

    public RegulationsConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    //Command bus
    @RequestScoped
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(RegulateUnsubscribedTradesman.class, new RegulateUnsubscribedTradesmanHandler(regulationsRepository(), appConfiguration.domainEventDispatcher()));
        commandHandlerMap.put(CreateMemberRegulation.class, new CreateMemberRegulationHandler(regulationsRepository()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @RequestScoped
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Repository
    private RegulationsRepository regulationsRepository() {
        return  InMemoryRegulationsRepository.getInstance();
    }

}
