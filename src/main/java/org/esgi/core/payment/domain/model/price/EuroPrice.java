package org.esgi.core.payment.domain.model.price;

import static org.esgi.core.payment.domain.model.price.DeviseType.EURO;

public class EuroPrice implements Price {

  DeviseType deviseType;
  int        value;

  public EuroPrice(int value) {
    this.deviseType = EURO;
    this.value = value;
  }

  public static EuroPrice of(int value) {
    return new EuroPrice(value);
  }

  @Override
  public DeviseType getDevise() {
    return this.deviseType;
  }

  @Override
  public Integer getValue() {
    return this.value;
  }
}
