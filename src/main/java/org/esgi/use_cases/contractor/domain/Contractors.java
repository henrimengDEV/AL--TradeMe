package org.esgi.use_cases.contractor.domain;

import org.esgi.shared_kernel.annotations.Repository;

import java.util.List;

@Repository
public interface Contractors {
    int nextIdentity();
    Contractor findById(ContractorId id);
    List<Contractor> findAll();
    Void add(Contractor contractor);
    Void removeById(ContractorId id);
}
