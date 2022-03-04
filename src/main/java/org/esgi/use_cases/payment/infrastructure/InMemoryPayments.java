package org.esgi.use_cases.payment.infrastructure;

import org.esgi.kernel.RepositoryImpl;
import org.esgi.use_cases.payment.domain.Payment;
import org.esgi.use_cases.payment.domain.PaymentId;
import org.esgi.use_cases.payment.domain.Payments;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RepositoryImpl
public final class InMemoryPayments implements Payments {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<PaymentId, Payment> data = new ConcurrentHashMap<>();

    @Override
    public int nextIdentity() {
        return 0;
    }

    @Override
    public Payment findById(PaymentId id) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Void add(Payment payment) {
        return null;
    }

    @Override
    public Void removeById(PaymentId id) {
        return null;
    }
}
