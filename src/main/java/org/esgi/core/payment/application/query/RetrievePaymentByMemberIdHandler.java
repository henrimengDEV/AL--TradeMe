package org.esgi.core.payment.application.query;


import java.util.List;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.payment.domain.PaymentRepository;
import org.esgi.core.payment.domain.model.payment.Payment;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrievePaymentByMemberIdHandler implements QueryHandler<RetrievePaymentByMemberId, List<Payment>> {

  private final PaymentRepository paymentRepository;

  public RetrievePaymentByMemberIdHandler(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public List<Payment> handle(RetrievePaymentByMemberId query) {
    return paymentRepository.findByMemberId(MemberId.of(query.id));
  }
}
