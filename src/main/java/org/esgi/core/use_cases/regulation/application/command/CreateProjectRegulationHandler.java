package org.esgi.core.use_cases.regulation.application.command;

import org.esgi.core.use_cases.project.domain.project.ProjectId;
import org.esgi.core.use_cases.regulation.domain.ProjectRegulation;
import org.esgi.core.use_cases.regulation.domain.Regulation;
import org.esgi.core.use_cases.regulation.domain.RegulationId;
import org.esgi.core.use_cases.regulation.domain.RegulationsRepository;
import org.esgi.kernel.cqs.CommandHandler;

public class CreateProjectRegulationHandler implements
    CommandHandler<CreateProjectRegulation, RegulationId> {

  private final RegulationsRepository regulationsRepository;

  public CreateProjectRegulationHandler(RegulationsRepository regulationsRepository) {
    this.regulationsRepository = regulationsRepository;
  }


  @Override
  public RegulationId handle(CreateProjectRegulation command) {
    RegulationId regulationId = regulationsRepository.nextId();
    Regulation regulation = ProjectRegulation.of(
        regulationId,
        command.description,
        ProjectId.of(command.projectId)
    );

    return regulationsRepository.add(regulation).getRegulationId();
  }
}
