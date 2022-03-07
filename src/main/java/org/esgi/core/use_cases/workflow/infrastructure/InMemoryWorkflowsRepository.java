package org.esgi.core.use_cases.workflow.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.esgi.core.use_cases.workflow.domain.Workflow;
import org.esgi.core.use_cases.workflow.domain.WorkflowsId;
import org.esgi.core.use_cases.workflow.domain.WorkflowsRepository;
import org.esgi.kernel.exceptions.NoSuchEntityException;

public final class InMemoryWorkflowsRepository implements WorkflowsRepository {

  private final AtomicInteger              counter;
  private final Map<WorkflowsId, Workflow> data;

  private static final InMemoryWorkflowsRepository INSTANCE = new InMemoryWorkflowsRepository();

  public static InMemoryWorkflowsRepository getInstance() {
    return INSTANCE;
  }

  public InMemoryWorkflowsRepository() {
    this.counter = new AtomicInteger(0);

    this.data = new ConcurrentHashMap<>();
  }

  @Override
  public Workflow add(Workflow workflows) {
    data.put(workflows.getWorkflowsId(), workflows);
    return workflows;
  }

  @Override
  public Workflow update(Workflow workflows) {
    data.put(workflows.getWorkflowsId(), workflows);
    return data.get(workflows.getWorkflowsId());
  }

  @Override
  public WorkflowsId nextId() {
    return WorkflowsId.of(counter.incrementAndGet());
  }

  @Override
  public List<Workflow> findAll() {
    return data.values().stream().collect(Collectors.toList());
  }


  @Override
  public Workflow findById(WorkflowsId workflowsId) {
    final Workflow workflows = data.get(workflowsId);
    if (workflows == null) {
      throw new NoSuchEntityException("No workflow for " + workflowsId.getValue());
    }
    return workflows;
  }

}
