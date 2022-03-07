package org.esgi.core.use_cases.member.domain.event;


import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.workflow.domain.event.MemberRegistrationCompletedEvent;
import org.esgi.core.use_cases.workflow.infrastructure.NotificationsByMail;
import org.esgi.kernel.event.EventListener;

public class MemberRegistrationCompletedListener implements
    EventListener<MemberRegistrationCompletedEvent> {

  private final NotificationsByMail notificationsByMail;
  private final MemberRepository    memberRepository;

  public MemberRegistrationCompletedListener(NotificationsByMail notificationsByMail,
                                             MemberRepository memberRepository) {
    this.notificationsByMail = notificationsByMail;
    this.memberRepository = memberRepository;
  }

  @Override
  public void listenTo(MemberRegistrationCompletedEvent event) {
    Member member = memberRepository.findById(MemberId.of(event.getMemberId()));
    notificationsByMail.send(
        event.getMail(),
        " Hello sir " + member.getLastname() +
            ", you have complete the registration to TradeMe you are now active."
    );
  }
}
