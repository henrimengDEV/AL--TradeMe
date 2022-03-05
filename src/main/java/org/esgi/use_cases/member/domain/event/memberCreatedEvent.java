package org.esgi.use_cases.member.domain.event;

import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventId;
import org.esgi.use_cases.member.domain.model.Member;

import java.time.ZonedDateTime;

public class memberCreatedEvent implements DomainEvent {
    private final EventId       eventId;
    private final ZonedDateTime occurredDate;
    private final Member        member;

    public memberCreatedEvent(EventId eventId,
                              ZonedDateTime occurredDate,
                              Member member) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.member = member;
    }

    public static memberCreatedEvent withMember(Member member) {
        return new memberCreatedEvent(EventId.create(), ZonedDateTime.now(), member);
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
}
