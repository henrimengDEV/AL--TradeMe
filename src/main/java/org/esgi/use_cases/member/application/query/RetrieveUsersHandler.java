package org.esgi.use_cases.member.application.query;

import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.MemberRepository;

import java.util.List;

public class RetrieveUsersHandler implements QueryHandler<RetrieveUsers, List<Member>> {

    private final MemberRepository MemberRepository;

    public RetrieveUsersHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public List<Member> handle(RetrieveUsers query) {
        return MemberRepository.findAll();
    }
}
