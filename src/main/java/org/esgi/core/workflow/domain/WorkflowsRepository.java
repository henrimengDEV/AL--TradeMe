package org.esgi.core.workflow.domain;

import java.util.List;

public interface WorkflowsRepository {

  Workflow add(Workflow workflows);

  Workflow update(Workflow workflows);

  WorkflowsId nextId();

  List<Workflow> findAll();

  Workflow findById(WorkflowsId workflowsId);
}
