package org.esgi.core.use_cases.payment.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.model.Payment;
import org.esgi.controllers.membership.response.PaymentResponseAdapter;
import org.esgi.controllers.membership.response.PaymentsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class RetrievePaymentByMemberIdHandler implements QueryHandler<RetrievePaymentByMemberId, PaymentsResponse> {

    private final PaymentRepository paymentRepository;
    private final PaymentResponseAdapter paymentResponseAdapter;

    public RetrievePaymentByMemberIdHandler(PaymentRepository paymentRepository,
                                            PaymentResponseAdapter paymentResponseAdapter) {
        this.paymentRepository = paymentRepository;
        this.paymentResponseAdapter = paymentResponseAdapter;
    }

    @Override
    public PaymentsResponse handle(RetrievePaymentByMemberId query) {
        List<Payment> payments = paymentRepository.findByMemberId(MemberId.of(query.id));
        return new PaymentsResponse(
                payments.stream()
                        .map(paymentResponseAdapter::adapt)
                        .collect(Collectors.toList())
        );
    }
}
