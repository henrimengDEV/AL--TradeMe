package org.esgi.use_cases.member.domain;

import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

import java.util.List;

public interface MemberRepository {

    Member add(Member user);

    Member update(Member user);

    MemberId nextId();

    List<Member> findAll();

    Member findById(MemberId userId);

    List<Member> findByCity(String city);
}
