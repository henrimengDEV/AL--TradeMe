package org.esgi.core.payment.domain.model.payment;


import java.util.Objects;
import org.esgi.kernel.ValueObjectId;

public final class PaymentId implements ValueObjectId {

  private final int value;

  public PaymentId(int value) {
    this.value = value;
  }

  public static PaymentId of(int value) {
    return new PaymentId(value);
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
    PaymentId paymentId = (PaymentId) o;
    return value == paymentId.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "PaymentId{" + "value=" + value + '}';
  }
}
