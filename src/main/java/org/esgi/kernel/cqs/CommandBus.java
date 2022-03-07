package org.esgi.kernel.cqs;

public interface CommandBus {

  <C extends Command, R> R send(C command);
}