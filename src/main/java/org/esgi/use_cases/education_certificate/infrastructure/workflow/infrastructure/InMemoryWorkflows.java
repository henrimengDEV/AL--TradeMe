package org.esgi.use_cases.education_certificate.infrastructure.workflow.infrastructure;

import org.esgi.use_cases.education_certificate.infrastructure.workflow.domain.Workflow;
import org.esgi.use_cases.education_certificate.infrastructure.workflow.domain.WorkflowId;
import org.esgi.use_cases.education_certificate.infrastructure.workflow.domain.Workflows;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryWorkflows implements Workflows {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<WorkflowId, Workflow> data = new ConcurrentHashMap<>();

    @Override
    public int nextIdentity() {
        return 0;
    }

    @Override
    public Workflow findById(WorkflowId id) {
        return null;
    }

    @Override
    public List<Workflow> findAll() {
        return null;
    }

    @Override
    public Void add(Workflow Workflow) {
        return null;
    }

    @Override
    public Void removeById(WorkflowId id) {
        return null;
    }
}
