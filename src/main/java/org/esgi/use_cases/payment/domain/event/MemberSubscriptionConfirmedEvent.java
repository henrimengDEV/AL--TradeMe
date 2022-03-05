package org.esgi.use_cases.payment.domain.event;

import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventId;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.payment.domain.model.NoPayment;
import org.esgi.use_cases.payment.domain.model.Payment;

import java.time.ZonedDateTime;

public class MemberSubscriptionConfirmedEvent implements DomainEvent {
    private final EventId       eventId;
    private final ZonedDateTime occurredDate;
    private final Member        member;
    private final Payment       payment;

    public MemberSubscriptionConfirmedEvent(EventId eventId,
                                            ZonedDateTime occurredDate,
                                            Member member,
                                            Payment payment) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.member = member;
        this.payment = payment;
    }

    public static MemberSubscriptionConfirmedEvent withMember(Member member) {
        return new MemberSubscriptionConfirmedEvent(EventId.create(), ZonedDateTime.now(), member, new NoPayment());
    }

    @Override
    public EventId getId() {
        return this.eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return this.occurredDate;
    }

    public Member getMember() {
        return this.member;
    }
    public Payment getPayment() {
        return this.payment;
    }
}
