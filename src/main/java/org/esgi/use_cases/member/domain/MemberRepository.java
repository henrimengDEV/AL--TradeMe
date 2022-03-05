package org.esgi.use_cases.member.domain;

import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

import java.util.List;

public interface MemberRepository {

    Member add(Member member);

    Member update(Member member);

    MemberId nextId();

    List<Member> findAll();

    Member findById(MemberId memberId);

    List<Member> findByCity(String city);

    List<Member> findByRole(String role);
}
