package org.esgi.use_cases.member.application.event;


import org.esgi.kernel.event.ApplicationEvent;
import org.esgi.kernel.event.EventId;
import org.esgi.use_cases.member.application.PaymentDTO;
import org.esgi.use_cases.member.domain.model.MemberId;

import java.time.ZonedDateTime;

public class ProcessPaymentEvent implements ApplicationEvent {
    private final EventId       eventId;
    private final ZonedDateTime occurredDate;
    private final MemberId      memberId;
    private final PaymentDTO    payment;

    public ProcessPaymentEvent(EventId eventId,
                               ZonedDateTime occurredDate,
                               MemberId memberId,
                               PaymentDTO payment) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.memberId = memberId;
        this.payment = payment;
    }

    public static ProcessPaymentEvent withMember(MemberId memberId) {
        return new ProcessPaymentEvent(EventId.create(), ZonedDateTime.now(), memberId, null);
    }

    public static ApplicationEvent withPayment(MemberId memberId,
                                               PaymentDTO payment) {
        return new ProcessPaymentEvent(EventId.create(), ZonedDateTime.now(), memberId, payment);
    }

    @Override
    public EventId getId() {
        return this.eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return this.occurredDate;
    }

    public MemberId getMemberId() {
        return this.memberId;
    }

    public PaymentDTO getPayment() {
        return this.payment;
    }
}
