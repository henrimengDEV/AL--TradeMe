package org.esgi.core.use_cases.workflows;


import org.esgi.ApplicationConfiguration;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.*;
import org.esgi.core.use_cases.workflows.application.command.ProcessNewMember;
import org.esgi.core.use_cases.workflows.application.command.ProcessNewMemberHandler;
import org.esgi.core.use_cases.workflows.domain.WorkflowsRepository;
import org.esgi.core.use_cases.workflows.infrastructure.InMemoryWorkflowsRepository;
import org.esgi.core.use_cases.workflows.infrastructure.NotificationsByMail;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class WorkflowsConfiguration {
    private final ApplicationConfiguration appConfiguration;

    public WorkflowsConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    //Command bus
    @RequestScoped
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(ProcessNewMember.class, new ProcessNewMemberHandler(workflowsRepository(), notificationsByMail()));
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
    @Singleton
    public NotificationsByMail notificationsByMail() {
        return new NotificationsByMail();
    }
}
