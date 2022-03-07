package org.esgi.core.use_cases.payment.domain;

import org.esgi.core.use_cases.payment.domain.model.subscription.MonthlySubscriptionPlan;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionPlan;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionType;
import org.esgi.core.use_cases.payment.domain.model.subscription.YearlySubscriptionPlan;
import org.esgi.kernel.annotations.Pattern;
import org.esgi.kernel.exceptions.NotValidAttributeException;

@Pattern
public final class SubscriptionPlanFactory {

  private SubscriptionPlanFactory() {
    throw new AssertionError();
  }

  public static SubscriptionPlan create(SubscriptionType subscriptionType) {
    if (subscriptionType.equals(SubscriptionType.MONTHLY)) {
      return new MonthlySubscriptionPlan();
    }
    if (subscriptionType.equals(SubscriptionType.YEARLY)) {
      return new YearlySubscriptionPlan();
    }
    throw new NotValidAttributeException("No subscription corresponding to the given plan.");
  }

}
