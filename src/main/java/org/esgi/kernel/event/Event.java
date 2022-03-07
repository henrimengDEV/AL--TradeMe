package org.esgi.kernel.event;

import java.time.ZonedDateTime;

public interface Event {

  EventId getId();

  ZonedDateTime getOccurredDate();
}
