package org.esgi.shared_kernel.cqs;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}