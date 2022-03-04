package org.esgi.use_cases.education_certificate.infrastructure.workflow.application.command;

import org.esgi.kernel.CommandHandler;

public class MyCommandHandler implements CommandHandler<MyCommand, Void> {
    
    @Override
    public Void handle(MyCommand command) {
        return null;
    }
}
