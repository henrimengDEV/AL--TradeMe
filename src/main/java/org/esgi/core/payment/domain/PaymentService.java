package org.esgi.core.payment.domain;


import org.esgi.core.payment.domain.model.payment.Payment;

public interface PaymentService {

  Payment process(Payment payment);
}
