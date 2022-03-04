package org.esgi.use_cases.education_certificate.infrastructure.workflow.domain;

import org.esgi.kernel.Repository;

import java.util.List;

@Repository
public interface Workflows {
    int nextIdentity();
    Workflow findById(WorkflowId id);
    List<Workflow> findAll();
    Void add(Workflow Workflow);
    Void removeById(WorkflowId id);
}