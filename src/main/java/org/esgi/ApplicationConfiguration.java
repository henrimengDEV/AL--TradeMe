package org.esgi;

import io.quarkus.runtime.Startup;
import org.esgi.core.engines.RegulationsEngine;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.event.*;
import org.esgi.core.use_cases.member.MemberConfiguration;
import org.esgi.core.use_cases.member.domain.event.MemberCreatedEvent;
import org.esgi.core.use_cases.member.infrastructure.DefaultEventDispatcher;
import org.esgi.core.use_cases.member.MemberAccess;
import org.esgi.core.use_cases.payment.PaymentConfiguration;
import org.esgi.core.use_cases.payment.PaymentAccess;
import org.esgi.core.use_cases.projects.ProjectsConfiguration;
import org.esgi.core.use_cases.projects.ProjectsAccess;
import org.esgi.core.use_cases.regulations.RegulationsConfiguration;
import org.esgi.core.use_cases.regulations.domain.event.UnsubscribedMemberEvent;
import org.esgi.core.use_cases.regulations.RegulationsAccess;
import org.esgi.core.use_cases.workflows.WorkflowsConfiguration;
import org.esgi.core.use_cases.workflows.application.event.MemberCreatedEventListener;
import org.esgi.core.use_cases.workflows.application.event.UnsubscribedMemberEventListener;
import org.esgi.core.use_cases.workflows.WorkflowsAccess;

import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Startup
public class ApplicationConfiguration {

    private final MemberConfiguration    memberConfiguration;
    private final PaymentConfiguration   paymentConfiguration;
    private final WorkflowsConfiguration   workflowsConfiguration;
    private final ProjectsConfiguration    projectsConfiguration;
    private final RegulationsConfiguration regulationsConfiguration;

    public ApplicationConfiguration(MemberConfiguration memberConfiguration,
                                    PaymentConfiguration paymentConfiguration,
                                    WorkflowsConfiguration workflowsConfiguration,
                                    ProjectsConfiguration projectsConfiguration,
                                    RegulationsConfiguration regulationsConfiguration) {
        this.memberConfiguration = memberConfiguration;
        this.paymentConfiguration = paymentConfiguration;
        this.workflowsConfiguration = workflowsConfiguration;
        this.projectsConfiguration = projectsConfiguration;
        this.regulationsConfiguration = regulationsConfiguration;
    }

    //Application event bus
    @RequestScoped
    public EventDispatcher<ApplicationEvent> applicationEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        return new DefaultEventDispatcher(listenerMap);
    }

    //Domain event bus
    @RequestScoped
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

    //Engine services
    @Singleton
    public RegulationsEngine regulationsEngine(){
        return new RegulationsEngine(regulationsAccess());
    }


}
