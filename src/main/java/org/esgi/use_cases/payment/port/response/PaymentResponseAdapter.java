package org.esgi.use_cases.payment.port.response;


import org.esgi.shared_kernel.Adapter;
import org.esgi.use_cases.payment.domain.model.Payment;

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
