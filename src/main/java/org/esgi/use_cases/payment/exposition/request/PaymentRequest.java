package org.esgi.use_cases.payment.exposition.request;

import javax.validation.constraints.NotBlank;

public class PaymentRequest {

    @NotBlank
    public String methodOfPayment;

    @NotBlank
    public String subscriptionPlan;

    @NotBlank
    public String transactionId;

}
