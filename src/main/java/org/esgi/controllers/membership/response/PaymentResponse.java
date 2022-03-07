package org.esgi.controllers.membership.response;

@SuppressWarnings("all")
public class PaymentResponse {

  public String  transactionId;
  public int     memberId;
  public int     paymentId;
  public String  methodOfPaymentType;
  public String  subscriptionPlan;
  public String  price;
  public boolean done;


  public PaymentResponse(String transactionId,
                         int memberId,
                         int paymentId,
                         String methodOfPaymentType,
                         String subscriptionPlan,
                         String price,
                         boolean done) {
    this.transactionId = transactionId;
    this.memberId = memberId;
    this.paymentId = paymentId;
    this.methodOfPaymentType = methodOfPaymentType;
    this.subscriptionPlan = subscriptionPlan;
    this.price = price;
    this.done = done;
  }

  @Override
  public String toString() {
    return "PaymentResponse{" +
        "transactionId=" + transactionId +
        ", memberId=" + memberId +
        ", paymentId=" + paymentId +
        ", methodOfPaymentType='" + methodOfPaymentType + '\'' +
        ", subscriptionPlan='" + subscriptionPlan + '\'' +
        ", price='" + price + '\'' +
        ", done=" + done +
        '}';
  }
}
