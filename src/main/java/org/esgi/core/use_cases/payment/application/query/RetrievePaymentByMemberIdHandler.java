package org.esgi.core.use_cases.payment.application.query;


import java.util.List;
import java.util.stream.Collectors;
import org.esgi.controllers.membership.response.PaymentResponseAdapter;
import org.esgi.controllers.membership.response.PaymentsResponse;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrievePaymentByMemberIdHandler implements
    QueryHandler<RetrievePaymentByMemberId, PaymentsResponse> {

  private final PaymentRepository      paymentRepository;
  private final PaymentResponseAdapter paymentResponseAdapter;

  public RetrievePaymentByMemberIdHandler(PaymentRepository paymentRepository,
                                          PaymentResponseAdapter paymentResponseAdapter) {
    this.paymentRepository = paymentRepository;
    this.paymentResponseAdapter = paymentResponseAdapter;
  }

  @Override
  public PaymentsResponse handle(RetrievePaymentByMemberId query) {
    List<Payment> payments = paymentRepository.findByMemberId(MemberId.of(query.id));
    return new PaymentsResponse(
        payments.stream()
                .map(paymentResponseAdapter::adapt)
                .collect(Collectors.toList())
    );
  }
}
