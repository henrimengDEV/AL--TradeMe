package org.esgi.use_cases.workflow.domain;

import org.esgi.kernel.annotations.Repository;

import java.util.List;

@Repository
public interface Workflows {
    int nextIdentity();
    Workflow findById(WorkflowId id);
    List<Workflow> findAll();
    Void add(Workflow Workflow);
    Void removeById(WorkflowId id);
}
