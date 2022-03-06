package org.esgi.kernel;

public interface Adapter<S, D> {
  D adapt(S source);
}
