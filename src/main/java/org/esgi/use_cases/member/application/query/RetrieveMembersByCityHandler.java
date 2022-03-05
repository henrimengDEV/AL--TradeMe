package org.esgi.use_cases.member.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.model.Member;

import java.util.List;


public class RetrieveMembersByCityHandler implements QueryHandler<RetrieveMembersByCity, List<Member>> {

    private final org.esgi.use_cases.member.domain.MemberRepository MemberRepository;

    public RetrieveMembersByCityHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public List<Member> handle(RetrieveMembersByCity query) {
        return MemberRepository.findByCity(query.city);
    }
}
