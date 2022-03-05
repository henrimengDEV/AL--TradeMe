package org.esgi.use_cases.member.domain.event;


import org.esgi.kernel.event.EventListener;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.infrastructure.NotificationsByMail;

public class MemberCreatedEventListener implements EventListener<memberCreatedEvent> {

    private  final NotificationsByMail notificationsByMail;

    public MemberCreatedEventListener(NotificationsByMail notificationsByMail) {
        this.notificationsByMail = notificationsByMail;
    }

    @Override
    public void listenTo(memberCreatedEvent event) {
        Member member = event.getMember();
        notificationsByMail.send(member.getMail(), "You are succesfully registered");
    }
}
