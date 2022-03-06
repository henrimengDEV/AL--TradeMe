package org.esgi.shared_kernel;

public interface Adapter<S, D> {
  D adapt(S source);
}
