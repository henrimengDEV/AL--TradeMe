package org.esgi.core.use_cases.payment.domain;


import org.esgi.kernel.annotations.Pattern;
import org.esgi.kernel.exceptions.NotValidAttributeException;
import org.esgi.core.use_cases.payment.domain.model.MethodOfPaymentType;

@Pattern
public final class PaymentStrategyFactory {

    private PaymentStrategyFactory() {
        throw new AssertionError();
    }

    public static PaymentStrategy getStrategy(MethodOfPaymentType methodOfPaymentType) {
        if (methodOfPaymentType.equals(MethodOfPaymentType.PAYPAL))
            return new PaypalPaymentStrategy();
        if (methodOfPaymentType.equals(MethodOfPaymentType.CREDITCARD))
            return new CreditCardPaymentStrategy();
        throw new NotValidAttributeException("Unsupported method of payment.");
    }

}
