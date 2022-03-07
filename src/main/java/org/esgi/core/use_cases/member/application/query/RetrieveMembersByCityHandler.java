package org.esgi.core.use_cases.member.application.query;


import java.util.List;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.kernel.cqs.QueryHandler;


public class RetrieveMembersByCityHandler implements
    QueryHandler<RetrieveMembersByCity, List<Member>> {

  private final MemberRepository MemberRepository;

  public RetrieveMembersByCityHandler(MemberRepository MemberRepository) {
    this.MemberRepository = MemberRepository;
  }

  @Override
  public List<Member> handle(RetrieveMembersByCity query) {
    return MemberRepository.findByCity(query.city);
  }
}
