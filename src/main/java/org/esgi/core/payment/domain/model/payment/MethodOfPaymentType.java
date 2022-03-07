package org.esgi.core.payment.domain.model.payment;

public enum MethodOfPaymentType {
  CREDITCARD("credit_card"), PAYPAL("paypal");

  private final String paymentType;

  MethodOfPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public static MethodOfPaymentType fromString(String text) {
    for (MethodOfPaymentType val : MethodOfPaymentType.values()) {
      if (val.paymentType.equalsIgnoreCase(text)) {
        return val;
      }
    }
    return null;
  }

  public String getPaymentType() {
    return this.paymentType;
  }


}
