package org.esgi.use_cases.workflows.application.event;


import org.esgi.shared_kernel.event.EventListener;
import org.esgi.use_cases.regulations.domain.event.UnsubscribedMemberEvent;
import org.esgi.use_cases.workflows.infrastructure.NotificationsByMail;

public class UnsubscribedMemberEventListener implements EventListener<UnsubscribedMemberEvent> {

    private final NotificationsByMail notificationsByMail;

    public UnsubscribedMemberEventListener(NotificationsByMail notificationsByMail) {
        this.notificationsByMail = notificationsByMail;
    }

    @Override
    public void listenTo(UnsubscribedMemberEvent event) {
        notificationsByMail.send(
                event.getMemberMail(),
                "Contractor are waiting you " + event.getMemberLogin() +
                " ! \n You can subscribe back with this exclusive offer of -35% ");
    }
}
