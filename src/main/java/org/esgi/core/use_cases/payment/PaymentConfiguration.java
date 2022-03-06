package org.esgi.core.use_cases.payment;


import org.esgi.ApplicationConfiguration;
import org.esgi.kernel.annotations.Configuration;
import org.esgi.kernel.cqs.*;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.infrastructure.InMemoryMemberRepository;
import org.esgi.core.use_cases.payment.application.PaymentServiceDefault;
import org.esgi.core.use_cases.payment.application.command.ProcessPayment;
import org.esgi.core.use_cases.payment.application.command.ProcessPaymentHandler;
import org.esgi.core.use_cases.payment.application.query.RetrievePaymentById;
import org.esgi.core.use_cases.payment.application.query.RetrievePaymentByIdHandler;
import org.esgi.core.use_cases.payment.application.query.RetrievePaymentByMemberId;
import org.esgi.core.use_cases.payment.application.query.RetrievePaymentByMemberIdHandler;
import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.PaymentService;
import org.esgi.controllers.membership.response.PaymentResponseAdapter;
import org.esgi.core.use_cases.payment.infrastructure.InMemoryPaymentRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class PaymentConfiguration {
    private final ApplicationConfiguration appConfiguration;

    public PaymentConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}


    //Command bus
    @RequestScoped
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(ProcessPayment.class, new ProcessPaymentHandler(paymentService()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @RequestScoped
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrievePaymentById.class, new RetrievePaymentByIdHandler(paymentRepository()));
        queryHandlerMap.put(RetrievePaymentByMemberId.class, new RetrievePaymentByMemberIdHandler(paymentRepository(), paymentResponseAdapter()));
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

    //Adapter
    @RequestScoped
    private PaymentResponseAdapter paymentResponseAdapter() {
        return new PaymentResponseAdapter();
    }
}
