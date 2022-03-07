package org.esgi.core.payment.domain.model.payment;

import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.payment.domain.model.subscription.SubscriptionPlan;
import org.esgi.core.payment.domain.model.price.Price;

public final class DefaultPayment implements Payment {

  private final TransactionId       transactionId;
  private final MemberId            memberId;
  private final MethodOfPaymentType methodOfPaymentType;
  private final SubscriptionPlan    subscriptionPlan;
  private final Price               price;
  private       PaymentId           paymentId;
  private       boolean             done;

  public DefaultPayment(TransactionId transactionId, MemberId memberId, PaymentId paymentId,
                        MethodOfPaymentType methodOfPaymentType, SubscriptionPlan subscriptionPlan, Price price,
                        boolean done) {
    this.transactionId = transactionId;
    this.memberId = memberId;
    this.paymentId = paymentId;
    this.methodOfPaymentType = methodOfPaymentType;
    this.subscriptionPlan = subscriptionPlan;
    this.price = price;
    this.done = done;
  }

  public static DefaultPayment of(TransactionId transactionId, MemberId memberId, PaymentId paymentId,
                                  MethodOfPaymentType methodOfPaymentType, SubscriptionPlan subscriptionPlan,
                                  Price price, boolean done) {
    return new DefaultPayment(transactionId, memberId, paymentId, methodOfPaymentType, subscriptionPlan, price, done);
  }

  @Override
  public TransactionId getTransactionId() {
    return this.transactionId;
  }

  public MemberId getMemberId() {
    return this.memberId;
  }

  @Override
  public PaymentId getPaymentId() {
    return this.paymentId;
  }

  @Override
  public void addPaymentId(int paymentId) {
    this.paymentId = new PaymentId(paymentId);
  }

  @Override
  public MethodOfPaymentType getMethodOfPayment() {
    return this.methodOfPaymentType;
  }

  @Override
  public SubscriptionPlan getSubscriptionPlan() {
    return this.subscriptionPlan;
  }

  @Override
  public Price getPrice() {
    return this.price;
  }

  @Override
  public String getPriceToString() {
    return price.getValue() + price.getDevise().getValue();
  }

  @Override
  public void done() {
    this.done = true;
  }

  @Override
  public boolean isDone() {
    return this.done;
  }
}
