package org.esgi.core.use_cases.payment.application.command;

import java.util.Objects;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.PaymentService;
import org.esgi.core.use_cases.payment.domain.PriceFactory;
import org.esgi.core.use_cases.payment.domain.SubscriptionPlanFactory;
import org.esgi.core.use_cases.payment.domain.model.payment.DefaultPayment;
import org.esgi.core.use_cases.payment.domain.model.payment.MethodOfPaymentType;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.core.use_cases.payment.domain.model.payment.PaymentId;
import org.esgi.core.use_cases.payment.domain.model.payment.TransactionId;
import org.esgi.core.use_cases.payment.domain.model.price.Price;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionPlan;
import org.esgi.core.use_cases.payment.domain.model.subscription.SubscriptionType;
import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.cqs.CommandHandler;

@Service
public class ProcessMembershipPaymentHandler implements
    CommandHandler<ProcessMembershipPayment, PaymentId> {

  private final PaymentRepository paymentRepository;
  private final PaymentService    paymentService;

  public ProcessMembershipPaymentHandler(PaymentRepository paymentRepository,
                                         PaymentService paymentService) {
    this.paymentRepository = paymentRepository;
    this.paymentService = paymentService;
  }

  @Override
  public PaymentId handle(ProcessMembershipPayment command) {
    PaymentId paymentId = paymentRepository.nextId();
    MemberId memberId = MemberId.of(command.memberId);
    SubscriptionPlan subscriptionPlan = SubscriptionPlanFactory.create(
        Objects.requireNonNull(SubscriptionType.fromString(command.subscriptionPlan)));
    MethodOfPaymentType methodOfPaymentType = MethodOfPaymentType.fromString(
        command.methodOfPayment);
    Price price = PriceFactory.create(subscriptionPlan);
    Payment paymentToProceed = DefaultPayment.of(
        TransactionId.of(command.transactionId),
        memberId,
        paymentId,
        methodOfPaymentType,
        subscriptionPlan,
        price,
        false
    );

    Payment payment = paymentService.process(paymentToProceed);

    return paymentRepository.add(payment).getPaymentId();
  }
}
