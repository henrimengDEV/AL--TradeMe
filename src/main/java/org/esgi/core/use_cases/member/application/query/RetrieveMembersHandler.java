package org.esgi.core.use_cases.member.application.query;

import java.util.List;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.kernel.cqs.QueryHandler;

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
