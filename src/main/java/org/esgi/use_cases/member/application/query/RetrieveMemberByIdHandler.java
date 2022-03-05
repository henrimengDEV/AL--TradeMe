package org.esgi.use_cases.member.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

public class RetrieveMemberByIdHandler implements QueryHandler<RetrieveMemberById, Member> {

    private final org.esgi.use_cases.member.domain.MemberRepository MemberRepository;

    public RetrieveMemberByIdHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public Member handle(RetrieveMemberById query) {
        return MemberRepository.findById(MemberId.of(query.id));
    }
}
