package org.esgi.use_cases.payment.domain;


import org.esgi.shared_kernel.annotations.Pattern;
import org.esgi.shared_kernel.exceptions.NotValidAttributeException;
import org.esgi.use_cases.payment.domain.model.MethodOfPaymentType;

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
