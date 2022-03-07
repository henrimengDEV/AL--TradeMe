package org.esgi.core.use_cases.workflow.domain;

public final class DefaultWorkflow implements Workflow {

  private final WorkflowsId workflowsId;
  private final String      content;

  private DefaultWorkflow(WorkflowsId workflowsId, String content) {
    this.workflowsId = workflowsId;
    this.content = content;
  }

  public static DefaultWorkflow of(WorkflowsId workflowsId,
                                   String content) {
    return new DefaultWorkflow(workflowsId, content);
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
  public String toString() {
    return "DefaultWorkflow{" +
        "workflowsId=" + workflowsId +
        ", content='" + content + '\'' +
        '}';
  }
}
