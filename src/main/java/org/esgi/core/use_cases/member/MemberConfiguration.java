package org.esgi.core.use_cases.member;


import org.esgi.ApplicationConfiguration;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.*;
import org.esgi.core.use_cases.member.application.command.ChangeMemberSubscriptionStatus;
import org.esgi.core.use_cases.member.application.command.ChangeMemberSubscriptionStatusHandler;
import org.esgi.core.use_cases.member.application.command.CreateMember;
import org.esgi.core.use_cases.member.application.command.CreateMemberHandler;
import org.esgi.core.use_cases.member.application.query.*;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.infrastructure.InMemoryMemberRepository;
import org.esgi.controllers.membership.response.MemberResponseAdapter;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class MemberConfiguration {
    private final ApplicationConfiguration appConfiguration;

    public MemberConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}


    //Command bus
    @RequestScoped
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateMember.class, new CreateMemberHandler(memberRepository(), appConfiguration.domainEventDispatcher()));
        commandHandlerMap.put(ChangeMemberSubscriptionStatus.class, new ChangeMemberSubscriptionStatusHandler(memberRepository()));
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
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Repository beans
    private MemberRepository memberRepository() {
        return InMemoryMemberRepository.getInstance();
    }

    //Service beans
    @RequestScoped
    public MemberResponseAdapter memberResponseAdapter() {
        return new MemberResponseAdapter();
    }

}
