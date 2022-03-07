package org.esgi.core.use_cases.workflow.domain.event;

import java.time.ZonedDateTime;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventId;

public class MemberRegistrationCompletedEvent implements DomainEvent {

  private final EventId       eventId;
  private final ZonedDateTime occurredDate;
  private       Integer       memberId;
  private       Integer       paymentId;
  private       String        mail;

  private MemberRegistrationCompletedEvent(EventId eventId,
                                           ZonedDateTime occurredDate) {
    this.eventId = eventId;
    this.occurredDate = occurredDate;
  }

  public static MemberRegistrationCompletedEvent create() {
    return new MemberRegistrationCompletedEvent(EventId.create(), ZonedDateTime.now());
  }

  public MemberRegistrationCompletedEvent withMemberId(Integer memberId) {
    this.memberId = memberId;
    return this;
  }

  public MemberRegistrationCompletedEvent withPaymentId(Integer paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  public MemberRegistrationCompletedEvent withMail(String mail) {
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
