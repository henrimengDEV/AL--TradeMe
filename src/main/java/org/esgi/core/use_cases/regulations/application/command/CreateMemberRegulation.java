package org.esgi.core.use_cases.regulations.application.command;

import org.esgi.kernel.cqs.Command;

public class CreateMemberRegulation implements Command {
    public final Integer memberId;
    public final String  description;

    public CreateMemberRegulation(Integer memberId,
                                  String description) {
        this.memberId = memberId;
        this.description = description;
    }
}
