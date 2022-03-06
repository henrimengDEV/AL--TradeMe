package org.esgi.use_cases.payment.domain;


import org.esgi.shared_kernel.annotations.Pattern;
import org.esgi.shared_kernel.exceptions.PaymentException;
import org.esgi.use_cases.payment.domain.model.Payment;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Pattern
public class PaypalPaymentStrategy implements PaymentStrategy {
    private static final Logger LOGGER = Logger.getLogger(PaypalPaymentStrategy.class.getName());

    @Override
    public Payment pay(Payment payment) {
        LOGGER.info("Waiting for confirmation from paypal for transactionId : " + payment.getTransactionId() + "\n");

        if (Objects.isNull(payment.getTransactionId()))
            throw new PaymentException("Payment with Paypal failure");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        payment.done();
        LOGGER.info("TransactionId : " + payment.getTransactionId() + " confirmed\n");
        return payment;
    }
}
