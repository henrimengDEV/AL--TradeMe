package org.esgi.use_cases.member.domain;

import org.esgi.kernel.Repository;
import org.esgi.use_cases.contractor.domain.Contractor;

import java.util.List;

@Repository
public interface Members {
    int nextIdentity();
    Member findById(MemberId id);
    List<Member> findAll();
    Void add(Member member);
    Void removeById(MemberId id);
}
