package org.esgi.core.use_cases.project.domain.job;

import java.util.Objects;
import org.esgi.kernel.ValueObjectId;

public final class JobId implements ValueObjectId {

  private final int value;

  public JobId(int value) {
    this.value = value;
  }

  public static JobId of(int value) {
    return new JobId(value);
  }

  public int getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    JobId jobId = (JobId) o;
    return value == jobId.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "JobId{" +
        "value=" + value +
        '}';
  }
}
