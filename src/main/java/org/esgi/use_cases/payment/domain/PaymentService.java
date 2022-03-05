package org.esgi.use_cases.payment.domain;


import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.model.PaymentId;

public interface PaymentService {

    PaymentId process(MemberId memberId, String transactionId, String methodOfPayment, String subscriptionPlan);
}
