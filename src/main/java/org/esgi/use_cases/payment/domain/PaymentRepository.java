package org.esgi.use_cases.payment.domain;

import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.model.Payment;
import org.esgi.use_cases.payment.domain.model.PaymentId;

import java.util.List;

public interface PaymentRepository {

    Payment add(Payment payment);

    Payment update(Payment payment);

    PaymentId nextId();

    List<Payment> findAll();

    Payment findById(PaymentId paymentId);

    List<Payment> findByMemberId(MemberId memberId);
}
