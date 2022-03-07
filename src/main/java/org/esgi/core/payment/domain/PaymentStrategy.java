package org.esgi.core.payment.domain;

import org.esgi.core.payment.domain.model.payment.Payment;

public interface PaymentStrategy {

  Payment pay(Payment payment);
}
