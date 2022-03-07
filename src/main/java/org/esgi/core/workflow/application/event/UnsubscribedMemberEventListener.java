package org.esgi.core.workflow.application.event;


import org.esgi.core.regulation.domain.event.UnsubscribedMemberEvent;
import org.esgi.infrastructure.NotificationsByMail;
import org.esgi.kernel.event.EventListener;

public class UnsubscribedMemberEventListener implements EventListener<UnsubscribedMemberEvent> {

  private final NotificationsByMail notificationsByMail;

  public UnsubscribedMemberEventListener(NotificationsByMail notificationsByMail) {
    this.notificationsByMail = notificationsByMail;
  }

  @Override
  public void listenTo(UnsubscribedMemberEvent event) {
    notificationsByMail.send(event.getMemberMail(), "New projects are waiting you !" + event.getMemberLogin()
        + " ! \n You can subscribe back with this exclusive offer of -35% ");
  }
}
