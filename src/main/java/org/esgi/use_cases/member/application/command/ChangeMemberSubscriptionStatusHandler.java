package org.esgi.use_cases.member.application.command;

import org.esgi.shared_kernel.cqs.CommandHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

public class ChangeMemberSubscriptionStatusHandler implements CommandHandler<ChangeMemberSubscriptionStatus, MemberId> {

    private final MemberRepository memberRepository;

    public ChangeMemberSubscriptionStatusHandler(MemberRepository memberRepository) {this.memberRepository = memberRepository;}

    @Override
    public MemberId handle(ChangeMemberSubscriptionStatus command) {
        Member member = memberRepository.findById(MemberId.of(command.memberId));
        member.changeIsSubscribed(command.isSubscribed);
        return memberRepository.update(member).getMemberId();
    }
}
