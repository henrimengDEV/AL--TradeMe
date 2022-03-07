package org.esgi.core.use_cases.payment.domain.model.subscription;

import java.time.LocalDate;
import java.time.Period;

public class MonthlySubscriptionPlan implements SubscriptionPlan {

  SubscriptionType type;
  Period           period;

  public MonthlySubscriptionPlan() {
    this.type = SubscriptionType.MONTHLY;
    this.period = Period.between(LocalDate.now(), LocalDate.now().plusMonths(1));
  }

  @Override
  public SubscriptionType getSubscriptionType() {
    return this.type;
  }

  @Override
  public Period getPeriod() {
    return this.period;
  }
}
