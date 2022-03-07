package org.esgi.core.use_cases.regulation;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.esgi.ApplicationConfiguration;
import org.esgi.core.use_cases.regulation.application.command.CreateMemberRegulation;
import org.esgi.core.use_cases.regulation.application.command.CreateMemberRegulationHandler;
import org.esgi.core.use_cases.regulation.application.command.RegulateUnsubscribedTradesman;
import org.esgi.core.use_cases.regulation.application.command.RegulateUnsubscribedTradesmanHandler;
import org.esgi.core.use_cases.regulation.domain.RegulationsRepository;
import org.esgi.core.use_cases.regulation.infrastructure.InMemoryRegulationsRepository;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.Command;
import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.cqs.Query;
import org.esgi.kernel.cqs.QueryBus;
import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.kernel.cqs.SimpleCommandBus;
import org.esgi.kernel.cqs.SimpleQueryBus;

@Configuration
@Dependent
public class RegulationConfiguration {

  private final ApplicationConfiguration appConfiguration;

  public RegulationConfiguration(ApplicationConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
  }

  //Command bus
  @RequestScoped
  public CommandBus commandBus() {
    final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    commandHandlerMap.put(RegulateUnsubscribedTradesman.class,
                          new RegulateUnsubscribedTradesmanHandler(regulationsRepository(),
                                                                   appConfiguration.domainEventDispatcher()));
    commandHandlerMap.put(CreateMemberRegulation.class,
                          new CreateMemberRegulationHandler(regulationsRepository()));
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
    return InMemoryRegulationsRepository.getInstance();
  }

}
