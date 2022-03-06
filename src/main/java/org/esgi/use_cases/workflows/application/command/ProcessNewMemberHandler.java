package org.esgi.use_cases.workflows.application.command;

import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.payment.domain.model.PaymentId;
import org.esgi.use_cases.workflows.domain.PaymentWorkflows;
import org.esgi.use_cases.workflows.domain.Workflows;
import org.esgi.use_cases.workflows.domain.WorkflowsId;
import org.esgi.use_cases.workflows.domain.WorkflowsRepository;
import org.esgi.use_cases.workflows.infrastructure.NotificationsByMail;

public class ProcessNewMemberHandler implements CommandHandler<ProcessNewMember, WorkflowsId> {

    private final WorkflowsRepository workflowsRepository;
    private final NotificationsByMail notificationsByMail;

    public ProcessNewMemberHandler(WorkflowsRepository workflowsRepository,
                                   NotificationsByMail notificationsByMail) {this.workflowsRepository = workflowsRepository;
        this.notificationsByMail = notificationsByMail;
    }

    @Override
    public WorkflowsId handle(ProcessNewMember command) {
        WorkflowsId workflowsId = workflowsRepository.nextId();
        StringBuilder content = new StringBuilder();
        content.append("member: ");
        content.append(command.memberlogin);
        content.append(" paid: ");
        content.append(command.paymentAmount);
        content.append(" succesfully and has been registered.");

        Workflows workflows = PaymentWorkflows.of(
                workflowsId,
                content.toString(),
                MemberId.of(command.memberId),
                PaymentId.of(command.paymentId)
        );
        Workflows workflowsAdded = workflowsRepository.add(workflows);

        notificationsByMail.send(
                command.memberMail,
                " Hello sir " + command.memberName +
                        ", you have complete the registration to TradeMe and successfully paid " + command.paymentAmount + "."
        );

        return workflowsAdded.getWorkflowsId();
    }
}
