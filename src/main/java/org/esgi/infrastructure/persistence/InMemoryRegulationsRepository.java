package org.esgi.infrastructure.persistence;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.esgi.core.regulation.domain.Regulation;
import org.esgi.core.regulation.domain.RegulationId;
import org.esgi.core.regulation.domain.RegulationsRepository;
import org.esgi.kernel.exceptions.NoSuchEntityException;

public final class InMemoryRegulationsRepository implements RegulationsRepository {

  private static final InMemoryRegulationsRepository INSTANCE = new InMemoryRegulationsRepository();
  private final        AtomicInteger                 counter;
  private final        Map<RegulationId, Regulation> data;

  public InMemoryRegulationsRepository() {
    this.counter = new AtomicInteger(0);

    this.data = new ConcurrentHashMap<>();
  }

  public static InMemoryRegulationsRepository getInstance() {
    return INSTANCE;
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
