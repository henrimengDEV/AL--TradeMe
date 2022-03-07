package org.esgi.exposition.membership.response;

import java.util.List;

public class PaymentsResponse {

  public final List<PaymentResponse> payments;

  public PaymentsResponse(List<PaymentResponse> payments) {
    this.payments = payments;
  }
}
