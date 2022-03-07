package org.esgi.core.use_cases.member.domain.event;


import org.esgi.core.use_cases.workflow.infrastructure.NotificationsByMail;
import org.esgi.kernel.event.EventListener;

public class MemberCreatedEventListener implements EventListener<MemberCreatedEvent> {

  private final NotificationsByMail notificationsByMail;

  public MemberCreatedEventListener(NotificationsByMail notificationsByMail) {
    this.notificationsByMail = notificationsByMail;
  }

  @Override
  public void listenTo(MemberCreatedEvent event) {
    notificationsByMail.send(event.getMemberMail(),
                             "Welcome to TradeMe " + event.getMemberLogin() + " !");
  }
}
