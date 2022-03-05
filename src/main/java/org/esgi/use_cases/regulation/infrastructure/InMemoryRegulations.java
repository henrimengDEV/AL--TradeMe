package org.esgi.use_cases.regulation.infrastructure;

import org.esgi.kernel.RepositoryImpl;
import org.esgi.kernel.exceptions.NoSuchEntityException;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.domain.RegulationId;
import org.esgi.use_cases.regulation.domain.Regulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@RepositoryImpl
public class InMemoryRegulations implements Regulations {

    private static final InMemoryRegulations INSTANCE = new InMemoryRegulations();
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final AtomicInteger count = new AtomicInteger();
    private final Map<RegulationId, Regulation> data = new ConcurrentHashMap<>();

    private InMemoryRegulations() {
    }

    public static InMemoryRegulations getInstance() {
        return INSTANCE;
    }

    @Override
    public int nextIdentity() {
        return this.count.incrementAndGet();
    }

    @Override
    public Regulation findById(RegulationId id) {
        this.log.info(String.format("[InMemoryRegulations] findById -> item n°%s", id.getValue()));
        Regulation result = this.data.get(id);
        if (result == null) throw new NoSuchEntityException(String.format("Regulation n°%s doesn't exist", id.getValue()));
        return result;
    }

    @Override
    public List<Regulation> findAll() {
        this.log.info(String.format("[InMemoryRegulations] findAll -> %d items", this.data.size()));
        return new ArrayList<>(this.data.values());
    }

    @Override
    public RegulationId add(Regulation regulation) {
        this.log.info(String.format("[InMemoryRegulations] add -> item n°%s", regulation.getId().getValue()));
        this.data.put(regulation.getId(), regulation);
        return regulation.getId();
    }

    @Override
    public Void removeById(RegulationId id) {
        this.log.info(String.format("[InMemoryRegulations] remove -> item n°%s", id));
        this.data.remove(id);
        return null;
    }
}
