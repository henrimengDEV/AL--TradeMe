package org.esgi.use_cases.payment.domain.model;

import org.esgi.shared_kernel.annotations.Repository;

import java.util.List;

@Repository
public interface Payments {
    int nextIdentity();
    Payment findById(PaymentId id);
    List<Payment> findAll();
    Void add(Payment payment);
    Void removeById(PaymentId id);
}
