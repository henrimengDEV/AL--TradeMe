package org.esgi.controllers.membership.response;


import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.kernel.Adapter;

public class PaymentResponseAdapter implements Adapter<Payment, PaymentResponse> {

  @Override
  public PaymentResponse adapt(Payment source) {
    return new PaymentResponse(
        source.getTransactionId().getValue(),
        source.getMemberId().getValue(),
        source.getPaymentId().getValue(),
        source.getMethodOfPayment().getPaymentType(),
        source.getSubscriptionPlan().getSubscriptionType().getValue(),
        source.getPrice().getValue() + " " + source.getPrice().getDevise().getValue(),
        source.isDone()
    );
  }
}
