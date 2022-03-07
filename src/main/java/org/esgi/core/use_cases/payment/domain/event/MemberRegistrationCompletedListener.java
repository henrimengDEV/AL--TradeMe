package org.esgi.core.use_cases.payment.domain.event;


import org.esgi.core.use_cases.payment.domain.PaymentRepository;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.core.use_cases.payment.domain.model.payment.PaymentId;
import org.esgi.core.use_cases.workflow.domain.event.MemberRegistrationCompletedEvent;
import org.esgi.core.use_cases.workflow.infrastructure.NotificationsByMail;
import org.esgi.kernel.event.EventListener;

public class MemberRegistrationCompletedListener implements
    EventListener<MemberRegistrationCompletedEvent> {

  private final NotificationsByMail notificationsByMail;
  private final PaymentRepository   paymentRepository;

  public MemberRegistrationCompletedListener(NotificationsByMail notificationsByMail,
                                             PaymentRepository paymentRepository) {
    this.notificationsByMail = notificationsByMail;
    this.paymentRepository = paymentRepository;
  }

  @Override
  public void listenTo(MemberRegistrationCompletedEvent event) {
    Payment payment = paymentRepository.findById(PaymentId.of(event.getPaymentId()));
    notificationsByMail.send(
        event.getMail(),
        " You have succesfully paid  " + payment.getPrice().getValue() + payment.getPrice()
                                                                                .getDevise()
                                                                                .getValue() +
            ".  Your transaction ID is : " + payment.getTransactionId() + " ."
    );
  }
}
