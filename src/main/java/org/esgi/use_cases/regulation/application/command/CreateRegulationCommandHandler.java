package org.esgi.use_cases.regulation.application.command;

import org.esgi.kernel.CommandHandler;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.domain.RegulationId;
import org.esgi.use_cases.regulation.domain.Regulations;

public final class CreateRegulationCommandHandler implements CommandHandler<CreateRegulationCommand, Integer> {

    Regulations regulations;

    public CreateRegulationCommandHandler(Regulations regulations) {
        this.regulations = regulations;
    }

    @Override
    public Integer handle(CreateRegulationCommand command) {
        RegulationId newId = new RegulationId(this.regulations.nextIdentity());
        Regulation newRegulation = Regulation.of(newId);
        return this.regulations.add(newRegulation).getValue();
    }
}
