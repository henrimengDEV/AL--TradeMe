package org.esgi.use_cases.payment.domain.event;

import org.esgi.kernel.event.EventListener;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.infrastructure.NotificationsByMail;

public class MemberSubscriptionConfirmedEventListener implements EventListener<MemberSubscriptionConfirmedEvent> {

    private  final NotificationsByMail notificationsByMail;

    public MemberSubscriptionConfirmedEventListener(NotificationsByMail notificationsByMail) {
        this.notificationsByMail = notificationsByMail;
    }

    @Override
    public void listenTo(MemberSubscriptionConfirmedEvent event) {
        Member member = event.getMember();
        notificationsByMail.send(
                member.getMail(),
                "Payment is successful for " + event.getMember().getLogin() + " he is now subscribed for " +
                        event.getPayment().getSubscriptionPlan().getSubscriptionType().toString() + " plan !"
        );
    }
}
