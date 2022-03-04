package org.esgi.use_cases.member.infrastructure;

import org.esgi.kernel.RepositoryImpl;
import org.esgi.use_cases.member.domain.Member;
import org.esgi.use_cases.member.domain.MemberId;
import org.esgi.use_cases.member.domain.Members;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RepositoryImpl
public class InMemoryMembers implements Members {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<MemberId, Member> data = new ConcurrentHashMap<>();

    @Override
    public int nextIdentity() {
        return 0;
    }

    @Override
    public Member findById(MemberId id) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Void add(Member member) {
        return null;
    }

    @Override
    public Void removeById(MemberId id) {
        return null;
    }
}
