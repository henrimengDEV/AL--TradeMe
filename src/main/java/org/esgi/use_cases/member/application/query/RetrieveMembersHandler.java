package org.esgi.use_cases.member.application.query;

import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;

import java.util.List;

public class RetrieveMembersHandler implements QueryHandler<RetrieveMembers, List<Member>> {

    private final MemberRepository MemberRepository;

    public RetrieveMembersHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public List<Member> handle(RetrieveMembers query) {
        return MemberRepository.findAll();
    }
}