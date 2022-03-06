package org.esgi.use_cases.regulations.infrastructure;

import org.esgi.shared_kernel.exceptions.NoSuchEntityException;
import org.esgi.use_cases.regulations.domain.Regulation;
import org.esgi.use_cases.regulations.domain.RegulationId;
import org.esgi.use_cases.regulations.domain.RegulationsRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryRegulationsRepository implements RegulationsRepository {
    private final AtomicInteger                counter;
    private final Map<RegulationId, Regulation> data;

    private static final InMemoryRegulationsRepository INSTANCE = new InMemoryRegulationsRepository();

    public static InMemoryRegulationsRepository getInstance() {
        return INSTANCE;
    }

    public InMemoryRegulationsRepository() {
        this.counter = new AtomicInteger(0);

        this.data = new ConcurrentHashMap<>();
    }

    @Override
    public Regulation add(Regulation regulation) {
        data.put(regulation.getRegulationId(), regulation);
        return regulation;
    }

    @Override
    public RegulationId nextId() {
        return RegulationId.of(counter.incrementAndGet());
    }

    @Override
    public List<Regulation> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }


    @Override
    public Regulation findById(RegulationId regulationId) {
        final Regulation regulation = data.get(regulationId);
        if (regulation == null) {
            throw new NoSuchEntityException("No regulation for " + regulationId.getValue());
        }
        return regulation;
    }

}
