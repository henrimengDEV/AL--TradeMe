package org.esgi.core.member.application;

public class PaymentDTO {

  public String methodOfPayment;

  public String subscriptionPlan;

  public String transactionId;

  public PaymentDTO(String methodOfPayment, String subscriptionPlan, String transactionId) {
    this.methodOfPayment = methodOfPayment;
    this.subscriptionPlan = subscriptionPlan;
    this.transactionId = transactionId;
  }
}
