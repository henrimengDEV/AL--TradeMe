package org.esgi.core.use_cases.workflow.application.command;

import org.esgi.core.use_cases.workflow.domain.DefaultWorkflow;
import org.esgi.core.use_cases.workflow.domain.Workflow;
import org.esgi.core.use_cases.workflow.domain.WorkflowsId;
import org.esgi.core.use_cases.workflow.domain.WorkflowsRepository;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;

public class ProcessNewProjectHandler implements CommandHandler<ProcessNewProject, WorkflowsId> {

  private final WorkflowsRepository          workflowsRepository;
  private final EventDispatcher<DomainEvent> domainEventDispatcher;

  public ProcessNewProjectHandler(WorkflowsRepository workflowsRepository,
                                  EventDispatcher<DomainEvent> domainEventDispatcher) {
    this.workflowsRepository = workflowsRepository;
    this.domainEventDispatcher = domainEventDispatcher;
  }

  @Override
  public WorkflowsId handle(ProcessNewProject command) {
    WorkflowsId workflowsId = workflowsRepository.nextId();
    StringBuilder content = new StringBuilder();
    content.append("contractorId: ");
    content.append(command.contractorId);
    content.append(" activate new project: ");
    content.append(command.projectId);
    content.append(".");

    Workflow workflow = DefaultWorkflow.of(
        workflowsId,
        content.toString()
    );
    Workflow workflowsAdded = workflowsRepository.add(workflow);

//        notificationsByMail.send(
//                command.contractorMail,
//                " Hello " + command.contractorName +
//                        ", you have complete the creation of a new project " + command.projectName + " to TradeMe !"
//        );

    return workflowsAdded.getWorkflowsId();
  }
}
