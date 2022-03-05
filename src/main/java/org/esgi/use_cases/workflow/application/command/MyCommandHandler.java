package org.esgi.use_cases.workflow.application.command;

import org.esgi.kernel.cqs.CommandHandler;

public class MyCommandHandler implements CommandHandler<MyCommand, Void> {
    
    @Override
    public Void handle(MyCommand command) {
        return null;
    }
}
