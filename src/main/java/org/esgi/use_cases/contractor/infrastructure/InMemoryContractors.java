package org.esgi.use_cases.contractor.infrastructure;

import org.esgi.kernel.RepositoryImpl;
import org.esgi.use_cases.contractor.domain.Contractor;
import org.esgi.use_cases.contractor.domain.ContractorId;
import org.esgi.use_cases.contractor.domain.Contractors;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RepositoryImpl
public final class InMemoryContractors implements Contractors {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<ContractorId, Contractor> data = new ConcurrentHashMap<>();

    @Override
    public int nextIdentity() {
        return 0;
    }

    @Override
    public Contractor findById(ContractorId id) {
        return null;
    }

    @Override
    public List<Contractor> findAll() {
        return null;
    }

    @Override
    public Void add(Contractor contractor) {
        return null;
    }

    @Override
    public Void removeById(ContractorId id) {
        return null;
    }
}
