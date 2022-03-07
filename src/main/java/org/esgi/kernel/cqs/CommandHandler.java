package org.esgi.kernel.cqs;

public interface CommandHandler<C extends Command, R> {

  R handle(C command);
}
