package org.esgi.core.member.domain.model;

import java.util.Objects;
import org.esgi.kernel.ValueObjectId;

public final class MemberId implements ValueObjectId {

  private final int value;

  public MemberId(int value) {
    this.value = value;
  }

  public static MemberId of(int value) {
    return new MemberId(value);
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
    MemberId memberId = (MemberId) o;
    return value == memberId.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "MemberId{" + "value=" + value + '}';
  }
}
