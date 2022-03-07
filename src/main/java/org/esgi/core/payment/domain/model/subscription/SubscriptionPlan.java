package org.esgi.core.payment.domain.model.subscription;

import java.time.Period;

public interface SubscriptionPlan {

  SubscriptionType getSubscriptionType();

  Period getPeriod();
}
