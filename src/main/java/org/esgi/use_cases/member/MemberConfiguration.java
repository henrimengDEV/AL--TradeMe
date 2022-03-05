package org.esgi.use_cases.member;


import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.*;
import org.esgi.kernel.event.*;
import org.esgi.use_cases.member.application.command.CreateUser;
import org.esgi.use_cases.member.application.command.CreateUserCommandHandler;
import org.esgi.use_cases.member.application.event.ProcessPaymentEvent;
import org.esgi.use_cases.member.application.query.*;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.event.UserCreatedEvent;
import org.esgi.use_cases.member.domain.event.UserCreatedEventListener;
import org.esgi.use_cases.member.exposition.response.MemberResponseAdapter;
import org.esgi.use_cases.member.infrastructure.DefaultEventDispatcher;
import org.esgi.use_cases.member.infrastructure.InMemoryMemberRepository;
import org.esgi.use_cases.member.infrastructure.NotificationsByMail;
import org.esgi.use_cases.payment.application.command.ProcessPayment;
import org.esgi.use_cases.payment.application.command.ProcessPaymentCommandHandler;
import org.esgi.use_cases.payment.application.event.ProcessPaymentEventListener;
import org.esgi.use_cases.payment.application.PaymentServiceDefault;
import org.esgi.use_cases.payment.domain.PaymentRepository;
import org.esgi.use_cases.payment.domain.PaymentService;
import org.esgi.use_cases.payment.domain.event.MemberSubscriptionConfirmedEvent;
import org.esgi.use_cases.payment.domain.event.MemberSubscriptionConfirmedEventListener;
import org.esgi.use_cases.payment.infrastructure.InMemoryPaymentRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MemberConfiguration {

    //Application event bus
    @Dependent
    public EventDispatcher<ApplicationEvent> applicationEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(ProcessPaymentEvent.class, List.of(new ProcessPaymentEventListener(paymentService())));
        return new DefaultEventDispatcher(listenerMap);
    }

    //Domain event bus
    @Dependent
    public EventDispatcher<DomainEvent> domainEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(UserCreatedEvent.class, List.of(new UserCreatedEventListener(new NotificationsByMail())));
        listenerMap.put(MemberSubscriptionConfirmedEvent.class, List.of(new MemberSubscriptionConfirmedEventListener(new NotificationsByMail())));
        return new DefaultEventDispatcher(listenerMap);
    }

    //Command bus
    @Dependent
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateUser.class, new CreateUserCommandHandler(userRepository(), applicationEventDispatcher(), domainEventDispatcher()));
        commandHandlerMap.put(ProcessPayment.class, new ProcessPaymentCommandHandler(paymentService()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @Dependent
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveUsers.class, new RetrieveUsersHandler(userRepository()));
        queryHandlerMap.put(RetrieveUserById.class, new RetrieveUserByIdHandler(userRepository()));
        queryHandlerMap.put(RetrieveUsersByCity.class, new RetrieveUsersByCityHandler(userRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Repository beans
    @Singleton
    public MemberRepository userRepository() {
        return InMemoryMemberRepository.getInstance();
    }

    @Singleton
    public PaymentRepository paymentRepository() {
        return InMemoryPaymentRepository.getInstance();
    }

    //Service beans
    @Singleton
    public PaymentService paymentService() {
        return new PaymentServiceDefault(paymentRepository(), userRepository(), domainEventDispatcher());
    }

    //Adapter beans
    @Dependent
    public MemberResponseAdapter memberResponseAdapter() {
        return new MemberResponseAdapter();
    }


}
