package org.esgi.use_cases.workflows.domain;

import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.model.PaymentId;

public final class PaymentWorkflows implements Workflows {
    private final WorkflowsId workflowsId;
    private final String content;
    private final MemberId memberId;
    private final PaymentId paymentId;

    private PaymentWorkflows(WorkflowsId workflowsId,
                             String content,
                             MemberId memberId,
                             PaymentId paymentId) {
        this.workflowsId = workflowsId;
        this.content = content;
        this.memberId = memberId;
        this.paymentId = paymentId;
    }

    public static PaymentWorkflows of(WorkflowsId workflowsId,
                                      String content,
                                      MemberId memberId,
                                      PaymentId paymentId) {
        return new PaymentWorkflows(workflowsId, content, memberId, paymentId);
    }


    @Override
    public WorkflowsId getWorkflowsId() {
        return workflowsId;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public MemberId getMemberId() {
        return memberId;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    @Override
    public String toString() {
        return "PaymentWorkflows{" +
                "workflowsId=" + workflowsId +
                ", content='" + content + '\'' +
                ", memberId=" + memberId +
                ", paymentId=" + paymentId +
                '}';
    }
}
