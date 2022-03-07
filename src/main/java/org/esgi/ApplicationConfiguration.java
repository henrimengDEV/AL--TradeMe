package org.esgi;

import io.quarkus.runtime.Startup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import org.esgi.core.engine.RegulationEngine;
import org.esgi.core.use_cases.member.MemberConfiguration;
import org.esgi.core.use_cases.member.domain.event.MemberCreatedEvent;
import org.esgi.core.use_cases.member.domain.event.MemberCreatedEventListener;
import org.esgi.core.use_cases.member.infrastructure.DefaultEventDispatcher;
import org.esgi.core.use_cases.member.port.MemberAccess;
import org.esgi.core.use_cases.payment.PaymentConfiguration;
import org.esgi.core.use_cases.payment.port.PaymentAccess;
import org.esgi.core.use_cases.project.ProjectConfiguration;
import org.esgi.core.use_cases.project.port.ProjectsAccess;
import org.esgi.core.use_cases.regulation.RegulationConfiguration;
import org.esgi.core.use_cases.regulation.domain.event.UnsubscribedMemberEvent;
import org.esgi.core.use_cases.regulation.port.RegulationsAccess;
import org.esgi.core.use_cases.workflow.WorkflowConfiguration;
import org.esgi.core.use_cases.workflow.application.event.UnsubscribedMemberEventListener;
import org.esgi.core.use_cases.workflow.domain.event.MemberRegistrationCompletedEvent;
import org.esgi.core.use_cases.workflow.port.WorkflowsAccess;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.event.ApplicationEvent;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.Event;
import org.esgi.kernel.event.EventDispatcher;
import org.esgi.kernel.event.EventListener;

@Configuration
@Startup
public class ApplicationConfiguration {

  private final MemberConfiguration     memberConfiguration;
  private final PaymentConfiguration    paymentConfiguration;
  private final WorkflowConfiguration   workflowsConfiguration;
  private final ProjectConfiguration    projectsConfiguration;
  private final RegulationConfiguration regulationsConfiguration;

  public ApplicationConfiguration(MemberConfiguration memberConfiguration,
                                  PaymentConfiguration paymentConfiguration,
                                  WorkflowConfiguration workflowsConfiguration,
                                  ProjectConfiguration projectsConfiguration,
                                  RegulationConfiguration regulationsConfiguration) {
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
    listenerMap.put(MemberCreatedEvent.class, List.of(
        new MemberCreatedEventListener(memberConfiguration.notificationsByMail())));
    listenerMap.put(MemberRegistrationCompletedEvent.class,
                    List.of(
                        new org.esgi.core.use_cases.member.domain.event.MemberRegistrationCompletedListener(
                            memberConfiguration.notificationsByMail(),
                            memberConfiguration.memberRepository()),
                        new org.esgi.core.use_cases.payment.domain.event.MemberRegistrationCompletedListener(
                            paymentConfiguration.notificationsByMail(),
                            paymentConfiguration.paymentRepository())
                    )
    );
    listenerMap.put(UnsubscribedMemberEvent.class, List.of(
        new UnsubscribedMemberEventListener(workflowsConfiguration.notificationsByMail())));
    return new DefaultEventDispatcher(listenerMap);
  }

  //Ressources Access
  @Singleton
  public MemberAccess memberAccess() {
    return new MemberAccess(memberConfiguration.commandBus(), memberConfiguration.queryBus(),
                            memberConfiguration.memberResponseAdapter());
  }

  @Singleton
  public PaymentAccess paymentAccess() {
    return new PaymentAccess(paymentConfiguration.commandBus(), paymentConfiguration.queryBus());
  }

  @Singleton
  public WorkflowsAccess workflowsAccess() {
    return new WorkflowsAccess(workflowsConfiguration.commandBus(),
                               workflowsConfiguration.queryBus());
  }

  @Singleton
  public ProjectsAccess projectsAccess() {
    return new ProjectsAccess(projectsConfiguration.commandBus(), projectsConfiguration.queryBus());
  }

  @Singleton
  public RegulationsAccess regulationsAccess() {
    return new RegulationsAccess(regulationsConfiguration.commandBus(),
                                 regulationsConfiguration.queryBus());
  }

  //Engine services
  @Singleton
  public RegulationEngine regulationsEngine() {
    return new RegulationEngine(regulationsAccess());
  }

}