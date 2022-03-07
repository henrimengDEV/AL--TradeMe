package org.esgi.core.use_cases.payment.domain.model.subscription;

import java.time.Period;

public interface SubscriptionPlan {

  SubscriptionType getSubscriptionType();

  Period getPeriod();
}
