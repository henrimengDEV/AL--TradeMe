package org.esgi.core.member.application.query;


import java.util.List;
import org.esgi.core.member.domain.MemberRepository;
import org.esgi.core.member.domain.model.Member;
import org.esgi.kernel.cqs.QueryHandler;


public class RetrieveMembersByCityHandler implements QueryHandler<RetrieveMembersByCity, List<Member>> {

  private final MemberRepository MemberRepository;

  public RetrieveMembersByCityHandler(MemberRepository MemberRepository) {
    this.MemberRepository = MemberRepository;
  }

  @Override
  public List<Member> handle(RetrieveMembersByCity query) {
    return MemberRepository.findByCity(query.city);
  }
}
