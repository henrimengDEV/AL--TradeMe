package org.esgi.use_cases.member.infrastructure;


import org.esgi.kernel.exceptions.NoSuchEntityException;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.domain.MemberRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryMemberRepository implements MemberRepository {
    private final AtomicInteger         counter;
    private final Map<MemberId, Member> data;

    private static final InMemoryMemberRepository INSTANCE = new InMemoryMemberRepository();

    public static InMemoryMemberRepository getInstance() {
        return INSTANCE;
    }

    public InMemoryMemberRepository() {
        this.counter = new AtomicInteger(0);

        this.data = new ConcurrentHashMap<>();
    }

    @Override
    public Member add(Member user) {
        data.put(user.getUserId(), user);
        user.addUserId(counter.get());
        return user;
    }

    @Override
    public Member update(Member user) {
        data.put(user.getUserId(), user);
        return data.get(user.getUserId());
    }


    @Override
    public MemberId nextId() {
        return MemberId.of(counter.incrementAndGet());
    }

    @Override
    public List<Member> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }

    @Override public Member findById(MemberId userId) {
        final Member user = data.get(userId);
        if (user == null) {
            throw new NoSuchEntityException("No member for " + userId.getValue());
        }
        return user;
    }

    @Override
    public List<Member> findByCity(String city) {
        List<Member> users = data.values()
                                 .stream()
                                 .filter(user -> user.getAddress().city().equalsIgnoreCase(city))
                                 .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new NoSuchEntityException("no members for the city " + city);
        }
        return users;
    }
}
