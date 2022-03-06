package org.esgi.use_cases.workflows.application.event;


import org.esgi.kernel.event.EventListener;
import org.esgi.use_cases.member.domain.event.MemberCreatedEvent;
import org.esgi.use_cases.workflows.infrastructure.NotificationsByMail;

public class MemberCreatedEventListener implements EventListener<MemberCreatedEvent> {

    private final NotificationsByMail notificationsByMail;

    public MemberCreatedEventListener(NotificationsByMail notificationsByMail) {
        this.notificationsByMail = notificationsByMail;
    }

    @Override
    public void listenTo(MemberCreatedEvent event) {
        notificationsByMail.send(event.getMemberMail(), "Welcome to TradeMe " + event.getMemberLogin() + " !");
    }
}
