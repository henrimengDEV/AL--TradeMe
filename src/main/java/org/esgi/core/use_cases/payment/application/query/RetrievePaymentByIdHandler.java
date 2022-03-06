package org.esgi.core.use_cases.payment.application.query;


import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.model.Payment;
import org.esgi.core.use_cases.payment.domain.model.PaymentId;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrievePaymentByIdHandler implements QueryHandler<RetrievePaymentById, Payment> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentByIdHandler(PaymentRepository paymentRepository) {this.paymentRepository = paymentRepository;;
    }

    @Override
public Payment handle(RetrievePaymentById query) {
        return paymentRepository.findById(PaymentId.of(query.id));
    }
}
