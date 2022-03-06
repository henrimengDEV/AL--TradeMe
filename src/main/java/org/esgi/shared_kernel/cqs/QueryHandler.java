package org.esgi.shared_kernel.cqs;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
