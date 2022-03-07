package org.esgi.core.regulation.domain;

import java.util.List;

public interface RegulationsRepository {

  Regulation add(Regulation regulation);

  RegulationId nextId();

  List<Regulation> findAll();

  Regulation findById(RegulationId regulationId);
}
