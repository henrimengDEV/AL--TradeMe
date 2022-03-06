package org.esgi.core.use_cases.payment.domain.model;

import java.time.Period;

public interface SubscriptionPlan {

    SubscriptionType getSubscriptionType();

    Period getPeriod();
}
