package org.esgi.core.use_cases.payment.application.query;


import org.esgi.kernel.cqs.Query;

public class RetrievePaymentById implements Query {

  public final int id;

  public RetrievePaymentById(int id) {
    this.id = id;
  }
}
