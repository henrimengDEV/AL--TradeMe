package org.esgi.shared_kernel.cqs;

public interface CommandBus {
    <C extends Command, R> R send(C command);
}