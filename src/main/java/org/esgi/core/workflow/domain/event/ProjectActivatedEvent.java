package org.esgi.core.workflow.domain.event;

import java.time.ZonedDateTime;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventId;

public class ProjectActivatedEvent implements DomainEvent {

  private final EventId       eventId;
  private final ZonedDateTime occurredDate;
  private       Integer       memberId;
  private       Integer       paymentId;
  private       String        mail;

  private ProjectActivatedEvent(EventId eventId, ZonedDateTime occurredDate) {
    this.eventId = eventId;
    this.occurredDate = occurredDate;
  }

  public static ProjectActivatedEvent create() {
    return new ProjectActivatedEvent(EventId.create(), ZonedDateTime.now());
  }

  public ProjectActivatedEvent withMemberId(Integer memberId) {
    this.memberId = memberId;
    return this;
  }

  public ProjectActivatedEvent withPaymentId(Integer paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  public ProjectActivatedEvent withMail(String mail) {
    this.mail = mail;
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


  public Integer getMemberId() {
    return memberId;
  }

  public Integer getPaymentId() {
    return paymentId;
  }

  public String getMail() {
    return mail;
  }
}
