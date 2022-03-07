package org.esgi.core.use_cases.workflow;


import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.esgi.ApplicationConfiguration;
import org.esgi.core.use_cases.workflow.application.command.ProcessNewMember;
import org.esgi.core.use_cases.workflow.application.command.ProcessNewMemberHandler;
import org.esgi.core.use_cases.workflow.application.command.ProcessNewProject;
import org.esgi.core.use_cases.workflow.application.command.ProcessNewProjectHandler;
import org.esgi.core.use_cases.workflow.domain.WorkflowsRepository;
import org.esgi.core.use_cases.workflow.infrastructure.InMemoryWorkflowsRepository;
import org.esgi.core.use_cases.workflow.infrastructure.NotificationsByMail;
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
public class WorkflowConfiguration {

  private final ApplicationConfiguration appConfiguration;

  public WorkflowConfiguration(ApplicationConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
  }

  //Command bus
  @RequestScoped
  public CommandBus commandBus() {
    final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    commandHandlerMap.put(ProcessNewMember.class, new ProcessNewMemberHandler(workflowsRepository(),
                                                                              appConfiguration.domainEventDispatcher()));
    commandHandlerMap.put(ProcessNewProject.class,
                          new ProcessNewProjectHandler(workflowsRepository(),
                                                       appConfiguration.domainEventDispatcher()));
    return new SimpleCommandBus(commandHandlerMap);
  }

  //Query bus
  @RequestScoped
  public QueryBus queryBus() {
    final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
    return new SimpleQueryBus(queryHandlerMap);
  }

  //Repository
  private WorkflowsRepository workflowsRepository() {
    return InMemoryWorkflowsRepository.getInstance();
  }

  //Infrastructure service
  @RequestScoped
  public NotificationsByMail notificationsByMail() {
    return new NotificationsByMail();
  }

}
