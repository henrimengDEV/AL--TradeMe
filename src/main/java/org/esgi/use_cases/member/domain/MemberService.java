package org.al_cc.TradeMe.use_cases.user.domain;

import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

import java.util.List;


public interface MemberService {

    Member save(Member user);

    Member update(Member user);

    Member getById(MemberId userId);

    MemberId getNextId();

    List<Member> getAll();

}
