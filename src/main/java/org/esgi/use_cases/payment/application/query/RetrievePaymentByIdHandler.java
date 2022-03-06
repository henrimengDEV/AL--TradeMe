package org.esgi.use_cases.payment.application.query;


import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.payment.domain.PaymentRepository;
import org.esgi.use_cases.payment.domain.model.Payment;
import org.esgi.use_cases.payment.domain.model.PaymentId;
import org.esgi.use_cases.payment.port.response.PaymentResponse;
import org.esgi.use_cases.payment.port.response.PaymentResponseAdapter;

public class RetrievePaymentByIdHandler implements QueryHandler<RetrievePaymentById, PaymentResponse> {

    private final PaymentRepository paymentRepository;
    private final PaymentResponseAdapter paymentResponseAdapter;

    public RetrievePaymentByIdHandler(PaymentRepository paymentRepository,
                                      PaymentResponseAdapter paymentResponseAdapter) {this.paymentRepository = paymentRepository;
        this.paymentResponseAdapter = paymentResponseAdapter;
    }

    @Override
    public PaymentResponse handle(RetrievePaymentById query) {
        Payment payment = paymentRepository.findById(PaymentId.of(query.id));
        return paymentResponseAdapter.adapt(payment);
    }
}
