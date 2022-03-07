package org.esgi.core.member.application.query;


import org.esgi.core.member.domain.MemberRepository;
import org.esgi.core.member.domain.model.Member;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrieveMemberByIdHandler implements QueryHandler<RetrieveMemberById, Member> {

  private final MemberRepository MemberRepository;

  public RetrieveMemberByIdHandler(MemberRepository MemberRepository) {
    this.MemberRepository = MemberRepository;
  }

  @Override
  public Member handle(RetrieveMemberById query) {
    return MemberRepository.findById(MemberId.of(query.id));
  }
}
