package org.esgi.use_cases.member.domain.event;

import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventId;
import org.esgi.use_cases.member.domain.model.Member;

import java.time.ZonedDateTime;

public class UserCreatedEvent implements DomainEvent {
    private final EventId       eventId;
    private final ZonedDateTime occurredDate;
    private final Member        user;

    public UserCreatedEvent(EventId eventId,
                            ZonedDateTime occurredDate,
                            Member user) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.user = user;
    }

    public static UserCreatedEvent withUser(Member user) {
        return new UserCreatedEvent(EventId.create(), ZonedDateTime.now(), user);
    }

    @Override
    public EventId getId() {
        return this.eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return this.occurredDate;
    }

    public Member getUser() {
        return this.user;
    }
}
