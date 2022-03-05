package org.esgi;

import io.quarkus.runtime.Startup;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.event.*;
import org.esgi.use_cases.member.MemberConfiguration;
import org.esgi.use_cases.member.application.event.ProcessPaymentEvent;
import org.esgi.use_cases.member.domain.event.MemberCreatedEventListener;
import org.esgi.use_cases.member.domain.event.memberCreatedEvent;
import org.esgi.use_cases.member.exposition.MemberAccess;
import org.esgi.use_cases.member.infrastructure.DefaultEventDispatcher;
import org.esgi.use_cases.member.infrastructure.NotificationsByMail;
import org.esgi.use_cases.payment.PaymentConfiguration;
import org.esgi.use_cases.payment.application.event.ProcessPaymentEventListener;
import org.esgi.use_cases.payment.domain.event.MemberSubscriptionConfirmedEvent;
import org.esgi.use_cases.payment.domain.event.MemberSubscriptionConfirmedEventListener;
import org.esgi.use_cases.payment.exposition.PaymentAccess;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Startup
public class AppConfiguration {

    private final MemberConfiguration  memberConfiguration;
    private final PaymentConfiguration paymentConfiguration;

    public AppConfiguration(MemberConfiguration memberConfiguration,
                            PaymentConfiguration paymentConfiguration) {this.memberConfiguration = memberConfiguration;
        this.paymentConfiguration = paymentConfiguration;
    }

    //Application event bus
    @Singleton
    public EventDispatcher<ApplicationEvent> applicationEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(ProcessPaymentEvent.class, List.of(new ProcessPaymentEventListener(paymentConfiguration.paymentService())));
        return new DefaultEventDispatcher(listenerMap);
    }

    //Domain event bus
    @Singleton
    public EventDispatcher<DomainEvent> domainEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(memberCreatedEvent.class, List.of(new MemberCreatedEventListener(new NotificationsByMail())));
        listenerMap.put(MemberSubscriptionConfirmedEvent.class, List.of(new MemberSubscriptionConfirmedEventListener(new NotificationsByMail())));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Singleton
    public MemberAccess memberAccess() {
        return new MemberAccess(memberConfiguration.commandBus(), memberConfiguration.queryBus(), memberConfiguration.memberResponseAdapter());
    }

    @Singleton
    public PaymentAccess paymentAccess() {
        return new PaymentAccess(paymentConfiguration.commandBus(), paymentConfiguration.queryBus());
    }



}
