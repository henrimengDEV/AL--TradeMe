package org.esgi.core.use_cases.project.domain;

import org.esgi.core.use_cases.payment.domain.model.price.Price;

public final class DailyRemuneration {

  private final Price price;

  private DailyRemuneration(Price price) {
    this.price = price;
  }

  public static DailyRemuneration of(Price price) {
    return new DailyRemuneration(price);
  }

  public Price getPrice() {
    return price;
  }
}
