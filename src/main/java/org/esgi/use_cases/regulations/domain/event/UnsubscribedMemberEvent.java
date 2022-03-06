package org.esgi.use_cases.regulations.domain.event;

import org.esgi.shared_kernel.event.DomainEvent;
import org.esgi.shared_kernel.event.EventId;

import java.time.ZonedDateTime;

public class UnsubscribedMemberEvent implements DomainEvent {
    private final EventId       eventId;
    private final ZonedDateTime occurredDate;
    private  String        memberMail;
    private  String        memberLogin;

    private UnsubscribedMemberEvent(EventId eventId,
                               ZonedDateTime occurredDate) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
    }

    public static UnsubscribedMemberEvent create() {
        return new UnsubscribedMemberEvent(EventId.create(), ZonedDateTime.now());
    }

    public  UnsubscribedMemberEvent withMail(String memberMail) {
        this.memberMail = memberMail;
        return this;
    }

    public  UnsubscribedMemberEvent withLogin(String memberLogin) {
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
