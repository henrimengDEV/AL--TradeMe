package org.esgi.use_cases.member.infrastructure;


import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.domain.MemberRepository;

import java.util.List;


public final class JdbcMemberRepository implements MemberRepository {

    @Override
    public Member add(Member user) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Member update(Member user) {
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
    public Member findById(MemberId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Member> findByCity(String city) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
