package org.esgi.use_cases.payment.domain;

import org.esgi.shared_kernel.annotations.Pattern;
import org.esgi.shared_kernel.exceptions.NotValidAttributeException;
import org.esgi.use_cases.payment.domain.model.MonthlySubscriptionPlan;
import org.esgi.use_cases.payment.domain.model.SubscriptionPlan;
import org.esgi.use_cases.payment.domain.model.SubscriptionType;
import org.esgi.use_cases.payment.domain.model.YearlySubscriptionPlan;

@Pattern
public final class SubscriptionPlanFactory {

    private SubscriptionPlanFactory() {
        throw new AssertionError();
    }

    public static SubscriptionPlan create(SubscriptionType subscriptionType) {
        if (subscriptionType.equals(SubscriptionType.MONTHLY))
            return new MonthlySubscriptionPlan();
        if (subscriptionType.equals(SubscriptionType.YEARLY))
            return new YearlySubscriptionPlan();
        throw new NotValidAttributeException("No subscription corresponding to the given plan.");
    }

}
