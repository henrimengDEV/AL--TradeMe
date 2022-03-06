package org.esgi.use_cases.payment.application.query;


import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.PaymentRepository;
import org.esgi.use_cases.payment.domain.model.Payment;

import java.util.List;

public class RetrievePaymentByMemberIdHandler implements QueryHandler<RetrievePaymentByMemberId, List<Payment>> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentByMemberIdHandler(PaymentRepository paymentRepository) {this.paymentRepository = paymentRepository;}

    @Override
    public List<Payment> handle(RetrievePaymentByMemberId query) {
        return paymentRepository.findByMemberId(MemberId.of(query.id));
    }
}
