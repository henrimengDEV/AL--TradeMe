package org.esgi.core.use_cases.payment.domain;


import org.esgi.kernel.annotations.Pattern;
import org.esgi.kernel.exceptions.PaymentException;
import org.esgi.core.use_cases.payment.domain.model.Payment;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Pattern
public class CreditCardPaymentStrategy implements PaymentStrategy {
    private static final Logger LOGGER = Logger.getLogger(CreditCardPaymentStrategy.class.getName());

    @Override
    public Payment pay(Payment payment) {

        LOGGER.info("Waiting for confirmation from Stripe for transactionId : " + payment.getTransactionId() + "\n");

        if (Objects.isNull(payment.getTransactionId()))
            throw new PaymentException("Payment with Stripe failure");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        payment.done();

        return payment;
    }
}
