package org.esgi.use_cases.member.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.domain.MemberRepository;

public class RetrieveUserByIdHandler implements QueryHandler<RetrieveUserById, Member> {

    private final org.esgi.use_cases.member.domain.MemberRepository MemberRepository;

    public RetrieveUserByIdHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public Member handle(RetrieveUserById query) {
        return MemberRepository.findById(MemberId.of(query.id));
    }
}
