package org.esgi.core.payment.application.query;


import org.esgi.kernel.cqs.Query;

public class RetrievePaymentByMemberId implements Query {

  public final int id;

  public RetrievePaymentByMemberId(int id) {
    this.id = id;
  }
}
