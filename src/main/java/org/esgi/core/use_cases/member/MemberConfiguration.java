package org.esgi.core.use_cases.member;


import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.esgi.ApplicationConfiguration;
import org.esgi.controllers.membership.response.MemberResponseAdapter;
import org.esgi.core.use_cases.member.application.command.ChangeMemberSubscriptionStatus;
import org.esgi.core.use_cases.member.application.command.ChangeMemberSubscriptionStatusHandler;
import org.esgi.core.use_cases.member.application.command.CreateMember;
import org.esgi.core.use_cases.member.application.command.CreateMemberHandler;
import org.esgi.core.use_cases.member.application.query.RetrieveMemberById;
import org.esgi.core.use_cases.member.application.query.RetrieveMemberByIdHandler;
import org.esgi.core.use_cases.member.application.query.RetrieveMembers;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersByCity;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersByCityHandler;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersByRole;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersByRoleHandler;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersHandler;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.infrastructure.InMemoryMemberRepository;
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
public class MemberConfiguration {

  private final ApplicationConfiguration appConfiguration;

  public MemberConfiguration(ApplicationConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
  }


  //Command bus
  @RequestScoped
  public CommandBus commandBus() {
    final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    commandHandlerMap.put(CreateMember.class, new CreateMemberHandler(memberRepository(),
                                                                      appConfiguration.domainEventDispatcher()));
    commandHandlerMap.put(ChangeMemberSubscriptionStatus.class,
                          new ChangeMemberSubscriptionStatusHandler(memberRepository()));
    return new SimpleCommandBus(commandHandlerMap);
  }

  //Query bus
  @RequestScoped
  public QueryBus queryBus() {
    final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
    queryHandlerMap.put(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
    queryHandlerMap.put(RetrieveMemberById.class,
                        new RetrieveMemberByIdHandler(memberRepository()));
    queryHandlerMap.put(RetrieveMembersByCity.class,
                        new RetrieveMembersByCityHandler(memberRepository()));
    queryHandlerMap.put(RetrieveMembersByRole.class,
                        new RetrieveMembersByRoleHandler(memberRepository()));
    return new SimpleQueryBus(queryHandlerMap);
  }

  //Repository beans
  public MemberRepository memberRepository() {
    return InMemoryMemberRepository.getInstance();
  }

  //Service beans
  @RequestScoped
  public MemberResponseAdapter memberResponseAdapter() {
    return new MemberResponseAdapter();
  }

  //Infrastructure service
  @RequestScoped
  public NotificationsByMail notificationsByMail() {
    return new NotificationsByMail();
  }
}
