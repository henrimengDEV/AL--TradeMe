package org.esgi.use_cases.workflows;


import org.esgi.ApplicationConfiguration;
import org.esgi.shared_kernel.annotations.Configuration;
import org.esgi.shared_kernel.cqs.*;
import org.esgi.use_cases.workflows.application.command.ProcessNewMember;
import org.esgi.use_cases.workflows.application.command.ProcessNewMemberHandler;
import org.esgi.use_cases.workflows.domain.WorkflowsRepository;
import org.esgi.use_cases.workflows.infrastructure.InMemoryWorkflowsRepository;
import org.esgi.use_cases.workflows.infrastructure.NotificationsByMail;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class WorkflowsConfiguration {
    private ApplicationConfiguration appConfiguration;

    //Command bus
    @Singleton
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(ProcessNewMember.class, new ProcessNewMemberHandler(workflowsRepository(), notificationsByMail()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @Singleton
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Repository
    private WorkflowsRepository workflowsRepository() {
        return InMemoryWorkflowsRepository.getInstance();
    }

    @Singleton
    public NotificationsByMail notificationsByMail() {
        return new NotificationsByMail();
    }
}
