package org.esgi.use_cases.payment.domain.model;

import org.esgi.use_cases.member.domain.model.MemberId;

public interface Payment {

    TransactionId getTransactionId();

    MemberId getMemberId();

    PaymentId getPaymentId();

    void addPaymentId(int paymentId);

    MethodOfPaymentType getMethodOfPayment();

    SubscriptionPlan getSubscriptionPlan();

    Price getPrice();

    void done();

    boolean isDone();


}
