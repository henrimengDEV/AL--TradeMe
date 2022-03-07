package org.esgi.core.use_cases.project;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.esgi.ApplicationConfiguration;
import org.esgi.core.use_cases.project.application.command.ActivateProject;
import org.esgi.core.use_cases.project.application.command.ActivateProjectHandler;
import org.esgi.core.use_cases.project.application.command.CreateProject;
import org.esgi.core.use_cases.project.application.command.CreateProjectHandler;
import org.esgi.core.use_cases.project.application.query.RetrieveProjectById;
import org.esgi.core.use_cases.project.application.query.RetrieveProjectByIdHandler;
import org.esgi.core.use_cases.project.application.query.RetrieveProjectsByMemberId;
import org.esgi.core.use_cases.project.application.query.RetrieveProjectsByMemberIdHandler;
import org.esgi.core.use_cases.project.domain.ProjectsRepository;
import org.esgi.core.use_cases.project.infrastructure.InMemoryProjectsRepository;
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
public class ProjectConfiguration {

  private final ApplicationConfiguration appConfiguration;

  public ProjectConfiguration(ApplicationConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
  }

  //Command bus
  @RequestScoped
  public CommandBus commandBus() {
    final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    commandHandlerMap.put(CreateProject.class, new CreateProjectHandler(projectsRepository()));
    commandHandlerMap.put(ActivateProject.class, new ActivateProjectHandler(projectsRepository()));
    return new SimpleCommandBus(commandHandlerMap);
  }

  //Query bus
  @RequestScoped
  public QueryBus queryBus() {
    final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
    queryHandlerMap.put(RetrieveProjectById.class,
                        new RetrieveProjectByIdHandler(projectsRepository()));
    queryHandlerMap.put(RetrieveProjectsByMemberId.class,
                        new RetrieveProjectsByMemberIdHandler(projectsRepository()));
    return new SimpleQueryBus(queryHandlerMap);
  }

  //Repository
  private ProjectsRepository projectsRepository() {
    return InMemoryProjectsRepository.getInstance();
  }

  //Infrastructure service
  @RequestScoped
  public NotificationsByMail notificationsByMail() {
    return new NotificationsByMail();
  }
}
