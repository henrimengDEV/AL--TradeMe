package org.esgi.core.contractor.domain;

import java.util.Objects;
import org.esgi.kernel.ValueObjectId;

public final class ContractorId implements ValueObjectId {

  private final int value;

  private ContractorId(int value) {
    this.value = value;
  }

  public static ContractorId of(int value) {
    return new ContractorId(value);
  }

  @Override
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
    ContractorId projectId = (ContractorId) o;
    return value == projectId.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "Contractor{" + "value=" + value + '}';
  }
}
