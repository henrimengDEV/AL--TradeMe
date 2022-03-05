package org.esgi.use_cases.regulation.infrastructure;

import org.esgi.kernel.RepositoryImpl;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.domain.RegulationId;
import org.esgi.use_cases.regulation.domain.Regulations;

import java.util.ArrayList;
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
        return this.count.incrementAndGet();
    }

    @Override
    public Regulation findById(RegulationId id) {
        return this.data.get(id);
    }

    @Override
    public List<Regulation> findAll() {
        return new ArrayList<>(this.data.values());
    }

    @Override
    public Void add(Regulation regulation) {
        this.data.put(regulation.getId(), regulation);
        return null;
    }

    @Override
    public Void removeById(RegulationId id) {
        this.data.remove(id);
        return null;
    }
}
