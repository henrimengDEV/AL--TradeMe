package org.esgi.core.use_cases.payment.domain.model.payment;


import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.payment.domain.model.price.Price;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionPlan;

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
