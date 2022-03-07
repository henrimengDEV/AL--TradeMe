package org.esgi.core.use_cases.member.infrastructure;


import java.util.List;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.member.domain.model.MemberId;


public final class JdbcMemberRepository implements MemberRepository {

  @Override
  public Member add(Member member) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public Member update(Member member) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public MemberId nextId() {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public List<Member> findAll() {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public Member findById(MemberId memberId) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public List<Member> findByCity(String city) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public List<Member> findByRole(String role) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }
}
