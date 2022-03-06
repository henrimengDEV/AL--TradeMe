package org.esgi.core.use_cases.regulations.application.command;

import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.regulations.domain.MemberRegulation;
import org.esgi.core.use_cases.regulations.domain.Regulation;
import org.esgi.core.use_cases.regulations.domain.RegulationId;
import org.esgi.core.use_cases.regulations.domain.RegulationsRepository;

public class CreateMemberRegulationHandler implements CommandHandler<CreateMemberRegulation, RegulationId> {

    private final RegulationsRepository regulationsRepository;

    public CreateMemberRegulationHandler(RegulationsRepository regulationsRepository) {this.regulationsRepository = regulationsRepository;
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
