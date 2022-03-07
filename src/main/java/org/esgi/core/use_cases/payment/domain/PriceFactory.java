package org.esgi.core.use_cases.payment.domain;


import org.esgi.core.use_cases.payment.domain.model.price.EuroPrice;
import org.esgi.core.use_cases.payment.domain.model.price.Price;
import org.esgi.core.use_cases.payment.domain.model.subscription.MonthlySubscriptionPlan;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionPlan;
import org.esgi.core.use_cases.payment.domain.model.subscription.YearlySubscriptionPlan;
import org.esgi.kernel.annotations.Pattern;
import org.esgi.kernel.exceptions.NotValidAttributeException;

@Pattern
public final class PriceFactory {

  private PriceFactory() {
    throw new AssertionError();
  }

  public static Price create(SubscriptionPlan subscriptionPlan) {
    if (subscriptionPlan instanceof MonthlySubscriptionPlan) {
      return EuroPrice.of(10);
    }
      if (subscriptionPlan instanceof YearlySubscriptionPlan) {
          return EuroPrice.of(150);
      }
    throw new NotValidAttributeException("Unsupported devise.");
  }

}
