package org.esgi.use_cases.payment.exposition.response;

import java.util.List;

public class PaymentsResponse {
    public final List<PaymentResponse> payments;

    public PaymentsResponse(List<PaymentResponse> payments) {
        this.payments = payments;
    }
}
