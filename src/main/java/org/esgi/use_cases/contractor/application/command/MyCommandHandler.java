package org.esgi.use_cases.contractor.application.command;

import org.esgi.kernel.CommandHandler;

public class MyCommandHandler implements CommandHandler<MyCommand, Void> {

    @Override
    public Void handle(MyCommand command) {
        return null;
    }
}