package org.esgi.use_cases.member.domain;

import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

import java.util.List;


public interface MemberService {

    Member save(Member member);

    Member update(Member member);

    Member getById(MemberId memberId);

    MemberId getNextId();

    List<Member> getAll();

}
