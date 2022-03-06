package org.esgi.use_cases.member.application.query;


import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.port.response.MemberResponse;
import org.esgi.use_cases.member.port.response.MemberResponseAdapter;

public class RetrieveMemberByIdHandler implements QueryHandler<RetrieveMemberById, MemberResponse> {

    private final MemberRepository      MemberRepository;
    private final MemberResponseAdapter memberResponseAdapter;

    public RetrieveMemberByIdHandler(MemberRepository MemberRepository,
                                     MemberResponseAdapter memberResponseAdapter) {
        this.MemberRepository = MemberRepository;
        this.memberResponseAdapter = memberResponseAdapter;
    }

    @Override
    public MemberResponse handle(RetrieveMemberById query) {
        Member member = MemberRepository.findById(MemberId.of(query.id));
        return memberResponseAdapter.adaptWithAll(member);
    }
}
