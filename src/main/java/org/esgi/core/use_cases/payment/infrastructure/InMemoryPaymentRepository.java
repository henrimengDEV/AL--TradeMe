package org.esgi.core.use_cases.payment.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.core.use_cases.payment.domain.model.payment.PaymentId;
import org.esgi.kernel.exceptions.NoSuchEntityException;

public final class InMemoryPaymentRepository implements PaymentRepository {

  private final AtomicInteger           counter;
  private final Map<PaymentId, Payment> data;

  private static final InMemoryPaymentRepository INSTANCE = new InMemoryPaymentRepository();

  public static InMemoryPaymentRepository getInstance() {
    return INSTANCE;
  }

  public InMemoryPaymentRepository() {
    this.counter = new AtomicInteger(0);

    this.data = new ConcurrentHashMap<>();
  }

  @Override
  public Payment add(Payment payment) {
    data.put(payment.getPaymentId(), payment);
    return payment;
  }

  @Override
  public Payment update(Payment payment) {
    data.put(payment.getPaymentId(), payment);
    return data.get(payment.getPaymentId());
  }

  @Override
  public PaymentId nextId() {
    return PaymentId.of(counter.incrementAndGet());
  }

  @Override
  public List<Payment> findAll() {
    return data.values().stream().collect(Collectors.toList());
  }

  @Override
  public Payment findById(PaymentId paymentId) {
    final Payment payment = data.get(paymentId);
    if (payment == null) {
      throw new NoSuchEntityException("No payment for " + paymentId.getValue());
    }
    return payment;
  }

  @Override
  public List<Payment> findByMemberId(MemberId memberId) {
    return data.values().stream().filter(value -> value.getMemberId().equals(memberId)).collect(
        Collectors.toList());
  }
}
