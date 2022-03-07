package org.esgi.core.payment.domain;

import java.util.List;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.payment.domain.model.payment.Payment;
import org.esgi.core.payment.domain.model.payment.PaymentId;

public interface PaymentRepository {

  Payment add(Payment payment);

  Payment update(Payment payment);

  PaymentId nextId();

  List<Payment> findAll();

  Payment findById(PaymentId paymentId);

  List<Payment> findByMemberId(MemberId memberId);
}
