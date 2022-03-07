package org.esgi.core.use_cases.contractor.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.esgi.core.use_cases.contractor.domain.Contractor;
import org.esgi.core.use_cases.contractor.domain.ContractorId;
import org.esgi.core.use_cases.contractor.domain.Contractors;
import org.esgi.kernel.annotations.RepositoryImpl;

@RepositoryImpl
public final class InMemoryContractors implements Contractors {

  private final AtomicInteger                 count = new AtomicInteger();
  private final Map<ContractorId, Contractor> data  = new ConcurrentHashMap<>();

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
