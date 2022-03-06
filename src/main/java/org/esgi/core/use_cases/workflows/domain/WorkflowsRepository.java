package org.esgi.core.use_cases.workflows.domain;

import java.util.List;

public interface WorkflowsRepository {

    Workflows add(Workflows workflows);

    Workflows update(Workflows workflows);

    WorkflowsId nextId();

    List<Workflows> findAll();

    Workflows findById(WorkflowsId workflowsId);
}
