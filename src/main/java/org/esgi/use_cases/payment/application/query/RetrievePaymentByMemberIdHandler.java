package org.esgi.use_cases.payment.application.query;


import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.PaymentRepository;
import org.esgi.use_cases.payment.domain.model.Payment;
import org.esgi.use_cases.payment.port.response.PaymentResponseAdapter;
import org.esgi.use_cases.payment.port.response.PaymentsResponse;

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
