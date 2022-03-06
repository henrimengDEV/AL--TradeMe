package org.esgi.use_cases.workflows.infrastructure;

import org.esgi.shared_kernel.exceptions.NoSuchEntityException;
import org.esgi.use_cases.workflows.domain.Workflows;
import org.esgi.use_cases.workflows.domain.WorkflowsId;
import org.esgi.use_cases.workflows.domain.WorkflowsRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryWorkflowsRepository implements WorkflowsRepository {
    private final AtomicInteger             counter;
    private final Map<WorkflowsId, Workflows> data;

    private static final InMemoryWorkflowsRepository INSTANCE = new InMemoryWorkflowsRepository();

    public static InMemoryWorkflowsRepository getInstance() {
        return INSTANCE;
    }

    public InMemoryWorkflowsRepository() {
        this.counter = new AtomicInteger(0);

        this.data = new ConcurrentHashMap<>();
    }

    @Override
    public Workflows add(Workflows workflows) {
        data.put(workflows.getWorkflowsId(), workflows);
        return workflows;
    }

    @Override
    public Workflows update(Workflows workflows) {
        data.put(workflows.getWorkflowsId(), workflows);
        return data.get(workflows.getWorkflowsId());
    }

    @Override
    public WorkflowsId nextId() {
        return WorkflowsId.of(counter.incrementAndGet());
    }

    @Override
    public List<Workflows> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }


    @Override
    public Workflows findById(WorkflowsId workflowsId) {
        final Workflows workflows = data.get(workflowsId);
        if (workflows == null) {
            throw new NoSuchEntityException("No workflow for " + workflowsId.getValue());
        }
        return workflows;
    }

}
