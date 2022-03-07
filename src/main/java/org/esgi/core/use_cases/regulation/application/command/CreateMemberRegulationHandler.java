package org.esgi.core.use_cases.regulation.application.command;

import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.regulation.domain.MemberRegulation;
import org.esgi.core.use_cases.regulation.domain.Regulation;
import org.esgi.core.use_cases.regulation.domain.RegulationId;
import org.esgi.core.use_cases.regulation.domain.RegulationsRepository;
import org.esgi.kernel.cqs.CommandHandler;

public class CreateMemberRegulationHandler implements
    CommandHandler<CreateMemberRegulation, RegulationId> {

  private final RegulationsRepository regulationsRepository;

  public CreateMemberRegulationHandler(RegulationsRepository regulationsRepository) {
    this.regulationsRepository = regulationsRepository;
  }


  @Override
  public RegulationId handle(CreateMemberRegulation command) {
    RegulationId regulationId = regulationsRepository.nextId();
    Regulation regulation = MemberRegulation.of(
        regulationId,
        command.description,
        MemberId.of(command.memberId)
    );

    return regulationsRepository.add(regulation).getRegulationId();
  }
}
