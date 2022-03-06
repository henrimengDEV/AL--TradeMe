package org.esgi;

import io.quarkus.runtime.Startup;
import org.esgi.shared_kernel.annotations.Configuration;
import org.esgi.shared_kernel.event.*;
import org.esgi.use_cases.member.MemberConfiguration;
import org.esgi.use_cases.member.domain.event.MemberCreatedEvent;
import org.esgi.use_cases.member.port.MemberAccess;
import org.esgi.use_cases.member.infrastructure.DefaultEventDispatcher;
import org.esgi.use_cases.payment.PaymentConfiguration;
import org.esgi.use_cases.payment.exposition.PaymentAccess;
import org.esgi.use_cases.projects.ProjectsConfiguration;
import org.esgi.use_cases.projects.exposition.ProjectsAccess;
import org.esgi.use_cases.regulations.RegulationsConfiguration;
import org.esgi.use_cases.regulations.domain.event.UnsubscribedMemberEvent;
import org.esgi.use_cases.regulations.exposition.RegulationsAccess;
import org.esgi.use_cases.workflows.WorkflowsConfiguration;
import org.esgi.use_cases.workflows.application.event.MemberCreatedEventListener;
import org.esgi.use_cases.workflows.application.event.UnsubscribedMemberEventListener;
import org.esgi.use_cases.workflows.exposition.WorkflowsAccess;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Startup
@ApplicationScoped
public class ApplicationConfiguration {

    private MemberConfiguration      memberConfiguration;
    private PaymentConfiguration     paymentConfiguration;
    private WorkflowsConfiguration   workflowsConfiguration;
    private ProjectsConfiguration    projectsConfiguration;
    private RegulationsConfiguration regulationsConfiguration;

    //Application event bus
    @Singleton
    public EventDispatcher<ApplicationEvent> applicationEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        return new DefaultEventDispatcher(listenerMap);
    }

    //Domain event bus
    @Singleton
    public EventDispatcher<DomainEvent> domainEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(MemberCreatedEvent.class, List.of(new MemberCreatedEventListener(workflowsConfiguration.notificationsByMail())));
        listenerMap.put(UnsubscribedMemberEvent.class, List.of(new UnsubscribedMemberEventListener(workflowsConfiguration.notificationsByMail())));
        return new DefaultEventDispatcher(listenerMap);
    }

    //Ressources Access
    @Singleton
    public MemberAccess memberAccess() {
        return new MemberAccess(memberConfiguration.commandBus(), memberConfiguration.queryBus(), memberConfiguration.memberResponseAdapter());
    }

    @Singleton
    public PaymentAccess paymentAccess() {
        return new PaymentAccess(paymentConfiguration.commandBus(), paymentConfiguration.queryBus());
    }

    @Singleton
    public WorkflowsAccess workflowsAccess() {
        return new WorkflowsAccess(workflowsConfiguration.commandBus(), workflowsConfiguration.queryBus());
    }

    @Singleton
    public ProjectsAccess projectsAccess() {
        return new ProjectsAccess(projectsConfiguration.commandBus(), projectsConfiguration.queryBus());
    }

    @Singleton
    public RegulationsAccess regulationsAccess() {
        return new RegulationsAccess(regulationsConfiguration.commandBus(), regulationsConfiguration.queryBus());
    }


}
