package org.esgi.use_cases.regulation.domain;

import org.esgi.kernel.annotations.Repository;

import java.util.List;

@Repository
public interface Regulations {
    int nextIdentity();
    Regulation findById(RegulationId id);
    List<Regulation> findAll();
    Void add(Regulation Regulation);
    Void removeById(RegulationId id);
}
