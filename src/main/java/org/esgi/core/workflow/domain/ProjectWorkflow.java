package org.esgi.core.workflow.domain;

import org.esgi.core.project.domain.project.ProjectId;
import org.esgi.core.contractor.domain.ContractorId;

public final class ProjectWorkflow implements Workflow {

  private final WorkflowsId  workflowsId;
  private final String       content;
  private final ProjectId    projectId;
  private final ContractorId contractorId;

  public ProjectWorkflow(WorkflowsId workflowsId, String content, ProjectId projectId, ContractorId contractorId) {
    this.workflowsId = workflowsId;
    this.content = content;
    this.projectId = projectId;
    this.contractorId = contractorId;
  }

  public static ProjectWorkflow of(WorkflowsId workflowsId, String content, ProjectId projectId,
                                   ContractorId contractorId) {
    return new ProjectWorkflow(workflowsId, content, projectId, contractorId);
  }

  @Override
  public WorkflowsId getWorkflowsId() {
    return workflowsId;
  }

  @Override
  public String getContent() {
    return content;
  }


  public ProjectId getProjectId() {
    return projectId;
  }

  public ContractorId getContractorId() {
    return contractorId;
  }
}
