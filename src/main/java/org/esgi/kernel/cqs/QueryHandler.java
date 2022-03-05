package org.esgi.kernel.cqs;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
