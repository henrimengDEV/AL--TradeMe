package org.esgi.core.member.application.query;


import java.util.Arrays;
import java.util.List;
import org.esgi.core.member.domain.MemberRepository;
import org.esgi.core.member.domain.model.Member;
import org.esgi.core.member.domain.model.MemberRole;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrieveMembersByRoleHandler implements QueryHandler<RetrieveMembersByRole, List<Member>> {

  private final MemberRepository MemberRepository;

  public RetrieveMembersByRoleHandler(MemberRepository MemberRepository) {
    this.MemberRepository = MemberRepository;
  }

  @Override
  public List<Member> handle(RetrieveMembersByRole query) {
    if (Arrays.stream(MemberRole.values()).anyMatch(value -> value.getValue().equals(query.role))) {
      return MemberRepository.findByRole(query.role);
    } else {
      throw new IllegalArgumentException("The role " + query.role + " doesn't exist");
    }
  }
}
