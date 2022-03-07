package org.esgi.core.payment.domain.model.payment;


import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.payment.domain.model.subscription.SubscriptionPlan;
import org.esgi.core.payment.domain.model.price.Price;

public class NoPayment implements Payment {

  @Override
  public TransactionId getTransactionId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public MemberId getMemberId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public PaymentId getPaymentId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addPaymentId(int paymentId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public MethodOfPaymentType getMethodOfPayment() {
    throw new UnsupportedOperationException();
  }

  @Override
  public SubscriptionPlan getSubscriptionPlan() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Price getPrice() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getPriceToString() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void done() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isDone() {
    throw new UnsupportedOperationException();
  }
}
