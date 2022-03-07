package org.esgi.core.payment.domain.model.subscription;

public enum SubscriptionType {
  MONTHLY("monthly"), YEARLY("yearly");

  private final String subscriptionType;

  SubscriptionType(String subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public static SubscriptionType fromString(String text) {
    for (SubscriptionType val : SubscriptionType.values()) {
      if (val.subscriptionType.equalsIgnoreCase(text)) {
        return val;
      }
    }
    return null;
  }

  public String getValue() {
    return this.subscriptionType;
  }


}
