package org.esgi.core.member.domain;

import java.util.List;
import org.esgi.core.member.domain.model.Member;
import org.esgi.core.member.domain.model.MemberId;

public interface MemberRepository {

  Member add(Member member);

  Member update(Member member);

  MemberId nextId();

  List<Member> findAll();

  Member findById(MemberId memberId);

  List<Member> findByCity(String city);

  List<Member> findByRole(String role);
}
