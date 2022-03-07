package org.esgi.core.use_cases.payment.domain;


import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.kernel.annotations.Pattern;
import org.esgi.kernel.exceptions.PaymentException;

@Pattern
public class PaypalPaymentStrategy implements PaymentStrategy {

  private static final Logger LOGGER = Logger.getLogger(PaypalPaymentStrategy.class.getName());

  @Override
  public Payment pay(Payment payment) {
    LOGGER.info(
        "Waiting for confirmation from paypal for transactionId : " + payment.getTransactionId()
            + "\n");

      if (Objects.isNull(payment.getTransactionId())) {
          throw new PaymentException("Payment with Paypal failure");
      }
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    payment.done();
    LOGGER.fine("TransactionId : " + payment.getTransactionId() + " confirmed\n");
    return payment;
  }
}
