package org.esgi.core.payment.application.query;


import org.esgi.core.payment.domain.PaymentRepository;
import org.esgi.core.payment.domain.model.payment.Payment;
import org.esgi.core.payment.domain.model.payment.PaymentId;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrievePaymentByIdHandler implements QueryHandler<RetrievePaymentById, Payment> {

  private final PaymentRepository paymentRepository;

  public RetrievePaymentByIdHandler(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
    ;
  }

  @Override
  public Payment handle(RetrievePaymentById query) {
    return paymentRepository.findById(PaymentId.of(query.id));
  }
}
