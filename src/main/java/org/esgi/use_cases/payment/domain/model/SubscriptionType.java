package org.esgi.use_cases.payment.domain.model;

public enum SubscriptionType {
    MONTHLY("monthly"),
    YEARLY("yearly");

    private final String subscriptionType;

    SubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getValue() {
        return this.subscriptionType;
    }

    public static SubscriptionType fromString(String text) {
        for (SubscriptionType val : SubscriptionType.values()) {
            if (val.subscriptionType.equalsIgnoreCase(text)) {
                return val;
            }
        }
        return null;
    }


}
