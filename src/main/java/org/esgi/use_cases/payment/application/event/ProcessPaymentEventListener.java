package org.esgi.use_cases.payment.application.event;


import org.esgi.kernel.event.EventListener;
import org.esgi.use_cases.member.application.event.ProcessPaymentEvent;
import org.esgi.use_cases.payment.domain.PaymentService;

public class ProcessPaymentEventListener implements EventListener<ProcessPaymentEvent> {

    private final PaymentService paymentService;


    public ProcessPaymentEventListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void listenTo(ProcessPaymentEvent event) {
        if (event.getPayment() != null)
            paymentService.process(event.getMemberId(), event.getPayment().transactionId, event.getPayment().methodOfPayment, event.getPayment().subscriptionPlan);
    }
}
