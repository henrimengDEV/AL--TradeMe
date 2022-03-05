package org.esgi.use_cases.member.domain.event;


import org.esgi.kernel.event.EventListener;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.infrastructure.NotificationsByMail;

public class UserCreatedEventListener implements EventListener<UserCreatedEvent> {

    private  final NotificationsByMail notificationsByMail;

    public UserCreatedEventListener(NotificationsByMail notificationsByMail) {
        this.notificationsByMail = notificationsByMail;
    }

    @Override
    public void listenTo(UserCreatedEvent event) {
        Member member = event.getUser();
        notificationsByMail.send(member.getMail(), "You are succesfully registered");
    }
}
