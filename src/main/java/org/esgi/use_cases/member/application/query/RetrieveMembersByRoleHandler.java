package org.esgi.use_cases.member.application.query;


import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberRole;
import org.esgi.use_cases.member.port.response.MemberResponseAdapter;
import org.esgi.use_cases.member.port.response.MembersResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RetrieveMembersByRoleHandler implements QueryHandler<RetrieveMembersByRole, MembersResponse> {

    private final MemberRepository MemberRepository;
    private final MemberResponseAdapter memberResponseAdapter;

    public RetrieveMembersByRoleHandler(MemberRepository MemberRepository,
                                        MemberResponseAdapter memberResponseAdapter) {
        this.MemberRepository = MemberRepository;
        this.memberResponseAdapter = memberResponseAdapter;
    }

    @Override
    public MembersResponse handle(RetrieveMembersByRole query) {
        if (Arrays.stream(MemberRole.values())
                  .anyMatch(value -> value.getValue().equals(query.role))
        ) {
            List<Member> members = MemberRepository.findByRole(query.role);
            return new MembersResponse(members.stream()
                                              .map(memberResponseAdapter::adapt)
                                              .collect(Collectors.toList())
            );
        }
        else
            throw new IllegalArgumentException("The role " + query.role + " doesn't exist");
    }
}
