package org.esgi.core.regulation.application.command;

import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.regulation.domain.Regulation;
import org.esgi.core.regulation.domain.RegulationId;
import org.esgi.core.regulation.domain.MemberRegulation;
import org.esgi.core.regulation.domain.RegulationsRepository;
import org.esgi.kernel.cqs.CommandHandler;

public class CreateMemberRegulationHandler implements CommandHandler<CreateMemberRegulation, RegulationId> {

  private final RegulationsRepository regulationsRepository;

  public CreateMemberRegulationHandler(RegulationsRepository regulationsRepository) {
    this.regulationsRepository = regulationsRepository;
  }


  @Override
  public RegulationId handle(CreateMemberRegulation command) {
    RegulationId regulationId = regulationsRepository.nextId();
    Regulation regulation = MemberRegulation.of(regulationId, command.description, MemberId.of(command.memberId));

    return regulationsRepository.add(regulation).getRegulationId();
  }
}
