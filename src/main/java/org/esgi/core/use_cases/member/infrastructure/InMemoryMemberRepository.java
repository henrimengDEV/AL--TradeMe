package org.esgi.core.use_cases.member.infrastructure;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.kernel.exceptions.NoSuchEntityException;

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
  public Member add(Member member) {
    data.put(member.getMemberId(), member);
    member.addMemberId(counter.get());
    return member;
  }

  @Override
  public Member update(Member member) {
    data.put(member.getMemberId(), member);
    return data.get(member.getMemberId());
  }


  @Override
  public MemberId nextId() {
    return MemberId.of(counter.incrementAndGet());
  }

  @Override
  public List<Member> findAll() {
    return data.values().stream().collect(Collectors.toList());
  }

  @Override
  public Member findById(MemberId memberId) {
    final Member member = data.get(memberId);
    if (member == null) {
      throw new NoSuchEntityException("No member for " + memberId.getValue());
    }
    return member;
  }

  @Override
  public List<Member> findByCity(String city) {
    List<Member> members = data.values()
                               .stream()
                               .filter(member -> member.getAddress().city().equalsIgnoreCase(city))
                               .collect(Collectors.toList());
    if (members.isEmpty()) {
      throw new NoSuchEntityException("no members for the city " + city);
    }
    return members;
  }

  @Override
  public List<Member> findByRole(String role) {
    List<Member> members = data.values()
                               .stream()
                               .filter(member -> member.getMemberRole().getValue()
                                                       .equalsIgnoreCase(role))
                               .collect(Collectors.toList());
    if (members.isEmpty()) {
      throw new NoSuchEntityException("no members for role " + role);
    }
    return members;
  }
}
