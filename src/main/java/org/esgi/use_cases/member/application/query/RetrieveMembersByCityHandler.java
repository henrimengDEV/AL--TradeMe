package org.esgi.use_cases.member.application.query;


import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.port.response.MemberResponseAdapter;
import org.esgi.use_cases.member.port.response.MembersResponse;

import java.util.List;
import java.util.stream.Collectors;


public class RetrieveMembersByCityHandler implements QueryHandler<RetrieveMembersByCity, MembersResponse> {

    private final MemberRepository MemberRepository;
    private final MemberResponseAdapter memberResponseAdapter;

    public RetrieveMembersByCityHandler(MemberRepository MemberRepository,
                                        MemberResponseAdapter memberResponseAdapter) {
        this.MemberRepository = MemberRepository;
        this.memberResponseAdapter = memberResponseAdapter;
    }

    @Override
    public MembersResponse handle(RetrieveMembersByCity query) {
        List<Member> members = MemberRepository.findByCity(query.city);
        return new MembersResponse(members.stream()
                                          .map(memberResponseAdapter::adaptWithAddress)
                                          .collect(Collectors.toList())
        );
    }
}
