package org.esgi.use_cases.regulation.infrastructure;

import org.esgi.kernel.annotations.RepositoryImpl;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.domain.RegulationId;
import org.esgi.use_cases.regulation.domain.Regulations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RepositoryImpl
public class InMemoryRegulations implements Regulations {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<RegulationId, Regulation> data = new ConcurrentHashMap<>();

    @Override
    public int nextIdentity() {
        return 0;
    }

    @Override
    public Regulation findById(RegulationId id) {
        return null;
    }

    @Override
    public List<Regulation> findAll() {
        return null;
    }

    @Override
    public Void add(Regulation Regulation) {
        return null;
    }

    @Override
    public Void removeById(RegulationId id) {
        return null;
    }
}
