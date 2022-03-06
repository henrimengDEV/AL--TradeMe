package org.esgi.shared_kernel.cqs;

public interface CommandHandler<C extends Command, R> {

    R handle(C command);
}
