package org.esgi.core.workflow.application.command;

import org.esgi.core.workflow.domain.DefaultWorkflow;
import org.esgi.core.workflow.domain.Workflow;
import org.esgi.core.workflow.domain.WorkflowsId;
import org.esgi.core.workflow.domain.WorkflowsRepository;
import org.esgi.core.workflow.domain.event.MemberRegistrationCompletedEvent;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;

public class ProcessNewMemberHandler implements CommandHandler<ProcessNewMember, WorkflowsId> {

  private final WorkflowsRepository          workflowsRepository;
  private final EventDispatcher<DomainEvent> domainEventDispatcher;

  public ProcessNewMemberHandler(WorkflowsRepository workflowsRepository,
                                 EventDispatcher<DomainEvent> domainEventDispatcher) {
    this.workflowsRepository = workflowsRepository;
    this.domainEventDispatcher = domainEventDispatcher;
  }

  @Override
  public WorkflowsId handle(ProcessNewMember command) {
    WorkflowsId workflowsId = workflowsRepository.nextId();
    StringBuilder content = new StringBuilder();
    content.append("member: ");
    content.append(command.memberId);
    content.append(" paid: ");
    content.append(command.paymentAmount);
    content.append(" paymentId: ");
    content.append(command.paymentId);
    content.append(" succesfully and has been registered.");

    Workflow workflows = DefaultWorkflow.of(workflowsId, content.toString());
    Workflow workflowsAdded = workflowsRepository.add(workflows);

    domainEventDispatcher.dispatch(
        MemberRegistrationCompletedEvent.create().withMail(command.memberMail).withMemberId(command.memberId)
                                        .withPaymentId(command.paymentId));

    return workflowsAdded.getWorkflowsId();
  }
}
