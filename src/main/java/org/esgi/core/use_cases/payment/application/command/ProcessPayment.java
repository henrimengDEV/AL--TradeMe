package org.esgi.core.use_cases.payment.application.command;

import org.esgi.kernel.cqs.Command;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class ProcessPayment implements Command {

    public String methodOfPayment;

    public String subscriptionPlan;

    public String transactionId;

    public Integer memberId;

    public ProcessPayment(String methodOfPayment,
                          String subscriptionPlan,
                          String transactionId,
                          Integer memberId) {
        this.methodOfPayment = methodOfPayment;
        this.subscriptionPlan = subscriptionPlan;
        this.transactionId = transactionId;
        this.memberId = memberId;
    }
}
