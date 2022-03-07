package org.esgi.core.payment.domain;


import org.esgi.core.payment.domain.model.payment.MethodOfPaymentType;
import org.esgi.kernel.annotations.Pattern;
import org.esgi.kernel.exceptions.NotValidAttributeException;

@Pattern
public final class PaymentStrategyFactory {

  private PaymentStrategyFactory() {
    throw new AssertionError();
  }

  public static PaymentStrategy getStrategy(MethodOfPaymentType methodOfPaymentType) {
    if (methodOfPaymentType.equals(MethodOfPaymentType.PAYPAL)) {
      return new PaypalPaymentStrategy();
    }
    if (methodOfPaymentType.equals(MethodOfPaymentType.CREDITCARD)) {
      return new CreditCardPaymentStrategy();
    }
    throw new NotValidAttributeException("Unsupported method of payment.");
  }

}
