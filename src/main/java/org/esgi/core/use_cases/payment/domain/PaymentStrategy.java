package org.esgi.core.use_cases.payment.domain;

import org.esgi.core.use_cases.payment.domain.model.payment.Payment;

public interface PaymentStrategy {

  Payment pay(Payment payment);
}
