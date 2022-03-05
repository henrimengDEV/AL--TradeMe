package org.esgi.use_cases.regulation.application.command;

import org.esgi.kernel.Command;

import java.time.LocalDate;

public final class CreateRegulationCommand implements Command {

    public int id;
    public LocalDate creationDate;

    private CreateRegulationCommand() {
    }

    public static CreateRegulationCommand of() {
        return new CreateRegulationCommand();
    }

    public CreateRegulationCommand id(int value) {
        CreateRegulationCommand command = new CreateRegulationCommand();
        command.id = value;
        command.creationDate = this.creationDate;
        return command;
    }

    public CreateRegulationCommand creationDate(LocalDate value) {
        CreateRegulationCommand command = new CreateRegulationCommand();
        command.id = this.id;
        command.creationDate = value;
        return command;
    }
}
