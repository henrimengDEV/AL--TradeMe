package org.esgi;

import io.quarkus.runtime.Startup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import org.esgi.core.member.application.command.ChangeMemberSubscriptionStatus;
import org.esgi.core.member.application.command.ChangeMemberSubscriptionStatusHandler;
import org.esgi.core.member.application.command.CreateMember;
import org.esgi.core.member.application.command.CreateMemberHandler;
import org.esgi.core.member.application.query.RetrieveMemberById;
import org.esgi.core.member.application.query.RetrieveMemberByIdHandler;
import org.esgi.core.member.application.query.RetrieveMembers;
import org.esgi.core.member.application.query.RetrieveMembersByCity;
import org.esgi.core.member.application.query.RetrieveMembersByCityHandler;
import org.esgi.core.member.application.query.RetrieveMembersByRole;
import org.esgi.core.member.application.query.RetrieveMembersByRoleHandler;
import org.esgi.core.member.application.query.RetrieveMembersHandler;
import org.esgi.core.payment.domain.event.MemberRegistrationCompletedListener;
import org.esgi.core.member.domain.MemberRepository;
import org.esgi.core.member.domain.event.MemberCreatedEvent;
import org.esgi.core.member.domain.event.MemberCreatedEventListener;
import org.esgi.core.payment.application.PaymentServiceDefault;
import org.esgi.core.payment.application.command.ProcessMembershipPayment;
import org.esgi.core.payment.application.command.ProcessMembershipPaymentHandler;
import org.esgi.core.payment.application.query.RetrievePaymentById;
import org.esgi.core.payment.application.query.RetrievePaymentByIdHandler;
import org.esgi.core.payment.application.query.RetrievePaymentByMemberId;
import org.esgi.core.payment.application.query.RetrievePaymentByMemberIdHandler;
import org.esgi.core.payment.domain.PaymentRepository;
import org.esgi.core.payment.domain.PaymentService;
import org.esgi.core.project.application.command.ActivateProject;
import org.esgi.core.project.application.command.ActivateProjectHandler;
import org.esgi.core.project.application.command.CreateProject;
import org.esgi.core.project.application.command.CreateProjectHandler;
import org.esgi.core.project.application.query.RetrieveProjectById;
import org.esgi.core.project.application.query.RetrieveProjectByIdHandler;
import org.esgi.core.project.application.query.RetrieveProjectsByMemberId;
import org.esgi.core.project.application.query.RetrieveProjectsByMemberIdHandler;
import org.esgi.core.project.domain.ProjectsRepository;
import org.esgi.core.regulation.application.command.CreateMemberRegulation;
import org.esgi.core.regulation.application.command.CreateMemberRegulationHandler;
import org.esgi.core.regulation.application.command.RegulateUnsubscribedTradesman;
import org.esgi.core.regulation.application.command.RegulateUnsubscribedTradesmanHandler;
import org.esgi.core.regulation.domain.RegulationsRepository;
import org.esgi.core.regulation.domain.event.UnsubscribedMemberEvent;
import org.esgi.core.workflow.application.command.ProcessNewMember;
import org.esgi.core.workflow.application.command.ProcessNewMemberHandler;
import org.esgi.core.workflow.application.command.ProcessNewProject;
import org.esgi.core.workflow.application.command.ProcessNewProjectHandler;
import org.esgi.core.workflow.application.event.UnsubscribedMemberEventListener;
import org.esgi.core.workflow.domain.WorkflowsRepository;
import org.esgi.core.workflow.domain.event.MemberRegistrationCompletedEvent;
import org.esgi.infrastructure.DefaultEventDispatcher;
import org.esgi.infrastructure.NotificationsByMail;
import org.esgi.infrastructure.RegulationEngine;
import org.esgi.infrastructure.persistence.InMemoryMemberRepository;
import org.esgi.infrastructure.persistence.InMemoryPaymentRepository;
import org.esgi.infrastructure.persistence.InMemoryProjectsRepository;
import org.esgi.infrastructure.persistence.InMemoryRegulationsRepository;
import org.esgi.infrastructure.persistence.InMemoryWorkflowsRepository;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.Command;
import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.cqs.Query;
import org.esgi.kernel.cqs.QueryBus;
import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.kernel.cqs.SimpleCommandBus;
import org.esgi.kernel.cqs.SimpleQueryBus;
import org.esgi.kernel.event.ApplicationEvent;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.Event;
import org.esgi.kernel.event.EventDispatcher;
import org.esgi.kernel.event.EventListener;

@Configuration
@Startup
public class ApplicationConfiguration {

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
    listenerMap.put(MemberCreatedEvent.class, List.of(new MemberCreatedEventListener(notificationsByMail())));
    listenerMap.put(MemberRegistrationCompletedEvent.class, List.of(
        new org.esgi.core.member.domain.event.MemberRegistrationCompletedListener(notificationsByMail(),
                                                                                  memberRepository()),
        new MemberRegistrationCompletedListener(notificationsByMail(),
                                                paymentRepository())));
    listenerMap.put(UnsubscribedMemberEvent.class, List.of(new UnsubscribedMemberEventListener(notificationsByMail())));
    return new DefaultEventDispatcher(listenerMap);
  }

  //Command bus
  @RequestScoped
  public CommandBus commandBus() {
    final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    commandHandlerMap.put(CreateMember.class, new CreateMemberHandler(memberRepository(), domainEventDispatcher()));
    commandHandlerMap.put(ChangeMemberSubscriptionStatus.class,
                          new ChangeMemberSubscriptionStatusHandler(memberRepository()));
    commandHandlerMap.put(ProcessNewMember.class,
                          new ProcessNewMemberHandler(workflowsRepository(), domainEventDispatcher()));
    commandHandlerMap.put(ProcessNewProject.class,
                          new ProcessNewProjectHandler(workflowsRepository(), domainEventDispatcher()));
    commandHandlerMap.put(RegulateUnsubscribedTradesman.class,
                          new RegulateUnsubscribedTradesmanHandler(regulationsRepository(), domainEventDispatcher()));
    commandHandlerMap.put(CreateMemberRegulation.class, new CreateMemberRegulationHandler(regulationsRepository()));
    commandHandlerMap.put(CreateProject.class, new CreateProjectHandler(projectsRepository()));
    commandHandlerMap.put(ActivateProject.class, new ActivateProjectHandler(projectsRepository()));
    commandHandlerMap.put(ProcessMembershipPayment.class,
                          new ProcessMembershipPaymentHandler(paymentRepository(), paymentService()));
    return new SimpleCommandBus(commandHandlerMap);
  }

  //Query bus
  @RequestScoped
  public QueryBus queryBus() {
    final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
    queryHandlerMap.put(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
    queryHandlerMap.put(RetrieveMemberById.class, new RetrieveMemberByIdHandler(memberRepository()));
    queryHandlerMap.put(RetrieveMembersByCity.class, new RetrieveMembersByCityHandler(memberRepository()));
    queryHandlerMap.put(RetrieveMembersByRole.class, new RetrieveMembersByRoleHandler(memberRepository()));
    queryHandlerMap.put(RetrieveProjectById.class, new RetrieveProjectByIdHandler(projectsRepository()));
    queryHandlerMap.put(RetrieveProjectsByMemberId.class, new RetrieveProjectsByMemberIdHandler(projectsRepository()));
    queryHandlerMap.put(RetrievePaymentById.class, new RetrievePaymentByIdHandler(paymentRepository()));
    queryHandlerMap.put(RetrievePaymentByMemberId.class,
                        new RetrievePaymentByMemberIdHandler(paymentRepository()));
    return new SimpleQueryBus(queryHandlerMap);
  }

  //Engines
  @RequestScoped
  public RegulationEngine regulationsEngine() {
    return new RegulationEngine(commandBus(), queryBus());
  }

  //Services
  @Singleton
  public PaymentService paymentService() {
    return new PaymentServiceDefault(paymentRepository(), memberRepository());
  }

  //Infrastructure services
  @Singleton
  private NotificationsByMail notificationsByMail() {
    return new NotificationsByMail();
  }

  //Repository
  private PaymentRepository paymentRepository() {
    return InMemoryPaymentRepository.getInstance();
  }

  private ProjectsRepository projectsRepository() {
    return InMemoryProjectsRepository.getInstance();
  }

  private MemberRepository memberRepository() {
    return InMemoryMemberRepository.getInstance();
  }

  private WorkflowsRepository workflowsRepository() {
    return InMemoryWorkflowsRepository.getInstance();
  }

  private RegulationsRepository regulationsRepository() {
    return InMemoryRegulationsRepository.getInstance();
  }

}