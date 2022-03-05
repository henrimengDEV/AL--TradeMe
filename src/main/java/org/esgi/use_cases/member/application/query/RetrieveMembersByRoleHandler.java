package org.esgi.use_cases.member.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberRole;

import java.util.Arrays;
import java.util.List;

public class RetrieveMembersByRoleHandler implements QueryHandler<RetrieveMembersByRole, List<Member>> {

    private final MemberRepository MemberRepository;

    public RetrieveMembersByRoleHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public List<Member> handle(RetrieveMembersByRole query) {
        if (Arrays.stream(MemberRole.values()).anyMatch(value -> value.getRole().equals(query.role)))
            return MemberRepository.findByRole(query.role);
        else
            throw new IllegalArgumentException("The role " + query.role + " doesn't exist");
    }
}
