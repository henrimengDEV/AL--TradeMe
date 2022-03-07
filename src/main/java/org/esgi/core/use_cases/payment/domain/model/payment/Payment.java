package org.esgi.core.use_cases.payment.domain.model.payment;

import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.payment.domain.model.price.Price;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionPlan;

public interface Payment {

  TransactionId getTransactionId();

  MemberId getMemberId();

  PaymentId getPaymentId();

  void addPaymentId(int paymentId);

  MethodOfPaymentType getMethodOfPayment();

  SubscriptionPlan getSubscriptionPlan();

  Price getPrice();

  String getPriceToString();

  void done();

  boolean isDone();


}
