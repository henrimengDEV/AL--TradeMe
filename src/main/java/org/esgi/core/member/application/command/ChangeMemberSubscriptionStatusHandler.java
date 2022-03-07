package org.esgi.core.member.application.command;

import org.esgi.core.member.domain.MemberRepository;
import org.esgi.core.member.domain.model.Member;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.kernel.cqs.CommandHandler;

public class ChangeMemberSubscriptionStatusHandler implements CommandHandler<ChangeMemberSubscriptionStatus, MemberId> {

  private final MemberRepository memberRepository;

  public ChangeMemberSubscriptionStatusHandler(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public MemberId handle(ChangeMemberSubscriptionStatus command) {
    Member member = memberRepository.findById(MemberId.of(command.memberId));
    member.changeIsSubscribed(command.isSubscribed);
    return memberRepository.update(member).getMemberId();
  }
}
