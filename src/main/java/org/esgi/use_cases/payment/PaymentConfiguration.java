package org.esgi.use_cases.payment;


import org.esgi.AppConfiguration;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.*;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.infrastructure.InMemoryMemberRepository;
import org.esgi.use_cases.payment.application.PaymentServiceDefault;
import org.esgi.use_cases.payment.application.command.ProcessPayment;
import org.esgi.use_cases.payment.application.command.ProcessPaymentHandler;
import org.esgi.use_cases.payment.domain.PaymentRepository;
import org.esgi.use_cases.payment.domain.PaymentService;
import org.esgi.use_cases.payment.infrastructure.InMemoryPaymentRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class PaymentConfiguration {

    private final AppConfiguration appConfiguration;

    public PaymentConfiguration(AppConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    //Command bus
    @Singleton
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(ProcessPayment.class, new ProcessPaymentHandler(paymentService()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @Singleton
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        return new SimpleQueryBus(queryHandlerMap);
    }

    //Service beans
    @Singleton
    public PaymentService paymentService() {
        return new PaymentServiceDefault(paymentRepository(), memberRepository());
    }

    //Repository
    private PaymentRepository paymentRepository() {
        return InMemoryPaymentRepository.getInstance();
    }

    private MemberRepository memberRepository() {
        return InMemoryMemberRepository.getInstance();
    }
}
