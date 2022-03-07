package org.esgi.core.use_cases.contractor.domain;

import java.util.List;
import org.esgi.kernel.annotations.Repository;

@Repository
public interface Contractors {

  int nextIdentity();

  Contractor findById(ContractorId id);

  List<Contractor> findAll();

  Void add(Contractor contractor);

  Void removeById(ContractorId id);
}
