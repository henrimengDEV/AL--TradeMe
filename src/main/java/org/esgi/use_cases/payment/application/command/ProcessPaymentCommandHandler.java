package org.esgi.use_cases.payment.application.command;

import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.annotations.Service;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.model.PaymentId;
import org.esgi.use_cases.payment.domain.PaymentService;

@Service
public class ProcessPaymentCommandHandler implements CommandHandler<ProcessPayment, PaymentId> {

    private final PaymentService paymentService;

    public ProcessPaymentCommandHandler(PaymentService paymentService) {this.paymentService = paymentService;}

    @Override
    public PaymentId handle(ProcessPayment command) {
        MemberId memberId = MemberId.of(command.memberId);
        return paymentService.process(memberId, command.transactionId, command.methodOfPayment, command.subscriptionPlan);
    }
}
