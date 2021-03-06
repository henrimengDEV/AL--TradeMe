package org.esgi.core.payment.application;


import java.util.logging.Logger;
import org.esgi.core.member.domain.MemberRepository;
import org.esgi.core.payment.domain.PaymentRepository;
import org.esgi.core.payment.domain.PaymentService;
import org.esgi.core.payment.domain.PaymentStrategy;
import org.esgi.core.payment.domain.PaymentStrategyFactory;
import org.esgi.core.payment.domain.model.payment.Payment;
import org.esgi.kernel.annotations.Service;

@Service
public class PaymentServiceDefault implements PaymentService {

  private static final Logger LOGGER = Logger.getLogger(PaymentServiceDefault.class.getName());

  private final PaymentRepository paymentRepository;
  private final MemberRepository  memberRepository;

  public PaymentServiceDefault(PaymentRepository paymentRepository, MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
    this.paymentRepository = paymentRepository;
  }

  @Override
  public Payment process(Payment paymentToProceed) {

    LOGGER.info("Payment started for transaction : " + paymentToProceed.getTransactionId() + "\n");

    PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(paymentToProceed.getMethodOfPayment());
    return paymentStrategy.pay(paymentToProceed);

  }
}
