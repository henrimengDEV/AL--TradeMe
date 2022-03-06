package org.esgi.use_cases.member.application.query;

import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.port.response.MemberResponseAdapter;
import org.esgi.use_cases.member.port.response.MembersResponse;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveMembersHandler implements QueryHandler<RetrieveMembers, MembersResponse> {

    private final MemberRepository      MemberRepository;
    private final MemberResponseAdapter memberResponseAdapter;

    public RetrieveMembersHandler(MemberRepository MemberRepository,
                                  MemberResponseAdapter memberResponseAdapter) {
        this.MemberRepository = MemberRepository;
        this.memberResponseAdapter = memberResponseAdapter;
    }

    @Override
    public MembersResponse handle(RetrieveMembers query) {
        List<Member> members = MemberRepository.findAll();
        return new MembersResponse(members.stream()
                                          .map(memberResponseAdapter::adapt)
                                          .collect(Collectors.toList())
        );
    }
}
