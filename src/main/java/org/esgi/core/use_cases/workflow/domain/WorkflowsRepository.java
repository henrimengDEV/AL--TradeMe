package org.esgi.core.use_cases.workflow.domain;

import java.util.List;

public interface WorkflowsRepository {

  Workflow add(Workflow workflows);

  Workflow update(Workflow workflows);

  WorkflowsId nextId();

  List<Workflow> findAll();

  Workflow findById(WorkflowsId workflowsId);
}
