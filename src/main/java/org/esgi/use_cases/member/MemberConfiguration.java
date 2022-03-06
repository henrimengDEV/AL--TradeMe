package org.esgi.use_cases.member;


import org.esgi.ApplicationConfiguration;
import org.esgi.shared_kernel.annotations.Configuration;
import org.esgi.shared_kernel.cqs.*;
import org.esgi.use_cases.member.application.command.ChangeMemberSubscriptionStatus;
import org.esgi.use_cases.member.application.command.ChangeMemberSubscriptionStatusHandler;
import org.esgi.use_cases.member.application.command.CreateMember;
import org.esgi.use_cases.member.application.command.CreateMemberHandler;
import org.esgi.use_cases.member.application.query.*;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.port.response.MemberResponseAdapter;
import org.esgi.use_cases.member.infrastructure.InMemoryMemberRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class MemberConfiguration {

    private final ApplicationConfiguration appConfiguration;

    public MemberConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    //Command bus
    @Singleton
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateMember.class, new CreateMemberHandler(memberRepository(), appConfiguration.domainEventDispatcher()));
        commandHandlerMap.put(ChangeMemberSubscriptionStatus.class, new ChangeMemberSubscriptionStatusHandler(memberRepository()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @Singleton
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository(), memberResponseAdapter()));
        queryHandlerMap.put(RetrieveMemberById.class, new RetrieveMemberByIdHandler(memberRepository(), memberResponseAdapter()));
        queryHandlerMap.put(RetrieveMembersByCity.class, new RetrieveMembersByCityHandler(memberRepository(), memberResponseAdapter()));
        queryHandlerMap.put(RetrieveMembersByRole.class, new RetrieveMembersByRoleHandler(memberRepository(), memberResponseAdapter()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Repository beans
    private MemberRepository memberRepository() {
        return InMemoryMemberRepository.getInstance();
    }

    //Service beans
    @Singleton
    public MemberResponseAdapter memberResponseAdapter() {
        return new MemberResponseAdapter();
    }

}
