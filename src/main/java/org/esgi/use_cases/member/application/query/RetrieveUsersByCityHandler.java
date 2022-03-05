package org.esgi.use_cases.member.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.MemberRepository;

import java.util.List;


public class RetrieveUsersByCityHandler implements QueryHandler<RetrieveUsersByCity, List<Member>> {

    private final org.esgi.use_cases.member.domain.MemberRepository MemberRepository;

    public RetrieveUsersByCityHandler(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    @Override
    public List<Member> handle(RetrieveUsersByCity query) {
        return MemberRepository.findByCity(query.city);
    }
}
