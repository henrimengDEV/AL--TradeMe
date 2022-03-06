package org.esgi.use_cases.member.domain.event;

import org.esgi.shared_kernel.event.DomainEvent;
import org.esgi.shared_kernel.event.EventId;

import java.time.ZonedDateTime;

public class MemberCreatedEvent implements DomainEvent {
    private final EventId       eventId;
    private final ZonedDateTime occurredDate;
    private  String        memberMail;
    private  String        memberLogin;

    private MemberCreatedEvent(EventId eventId,
                              ZonedDateTime occurredDate) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
    }

    public static MemberCreatedEvent create() {
        return new MemberCreatedEvent(EventId.create(), ZonedDateTime.now());
    }

    public  MemberCreatedEvent withMail(String memberMail) {
        this.memberMail = memberMail;
        return this;
    }

    public  MemberCreatedEvent withLogin(String memberLogin) {
        this.memberLogin = memberLogin;
        return this;
    }

    @Override
    public EventId getId() {
        return this.eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return this.occurredDate;
    }

    public String getMemberMail() {
        return this.memberMail;
    }

    public String getMemberLogin() {
        return memberLogin;
    }
}