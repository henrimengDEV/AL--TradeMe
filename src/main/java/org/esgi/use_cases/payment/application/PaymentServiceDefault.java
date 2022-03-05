package org.esgi.use_cases.payment.application;


import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;
import org.esgi.kernel.event.EventId;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.payment.domain.*;
import org.esgi.use_cases.payment.domain.event.MemberSubscriptionConfirmedEvent;
import org.esgi.use_cases.payment.domain.model.*;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PaymentServiceDefault implements PaymentService {
    private static final Logger LOGGER = Logger.getLogger(PaymentServiceDefault.class.getName());

    private final PaymentRepository            paymentRepository;
    private final MemberRepository             memberRepository;
    private final EventDispatcher<DomainEvent> domainEventDispatcher;

    public PaymentServiceDefault(PaymentRepository paymentRepository, MemberRepository memberRepository, EventDispatcher<DomainEvent> domainEventDispatcher) {
        this.memberRepository = memberRepository;
        this.paymentRepository = paymentRepository;
        this.domainEventDispatcher = domainEventDispatcher;
    }

    @Override
    public PaymentId process(MemberId memberId, String transactionId, String methodOfPayment, String subscriptionPlan) {
        Member    member      = this.memberRepository.findById(memberId);
        PaymentId paymentId = this.paymentRepository.nextId();
        Price     price     = EuroPrice.of(10);         //Fake retrieve price from database
        Payment paymentToProceed = DefaultPayment.of(
                TransactionId.of(transactionId),
                memberId,
                paymentId,
                MethodOfPaymentType.fromString(methodOfPayment),
                SubscriptionPlanFactory.create(Objects.requireNonNull(SubscriptionType.fromString(subscriptionPlan))),
                price,
                false
        );

        LOGGER.info("Payment started for : " + member.getLogin() + ", type= " + member.getMemberRole().toString() + "\n");

        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(
                Objects.requireNonNull(MethodOfPaymentType.fromString(methodOfPayment))
        );
        Payment payment = paymentStrategy.pay(paymentToProceed);
        var paymentResult = this.paymentRepository.add(payment);

        domainEventDispatcher.dispatch(new MemberSubscriptionConfirmedEvent(EventId.create(), ZonedDateTime.now(), member, payment));

        return paymentResult.getPaymentId();
    }
}
