package org.esgi.infrastructure.persistence;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.project.domain.ProjectsRepository;
import org.esgi.core.project.domain.project.Project;
import org.esgi.core.project.domain.project.ProjectId;
import org.esgi.kernel.exceptions.NoSuchEntityException;

public final class InMemoryProjectsRepository implements ProjectsRepository {

  private static final InMemoryProjectsRepository INSTANCE = new InMemoryProjectsRepository();
  private final        AtomicInteger              counter;
  private final        Map<ProjectId, Project>    data;

  public InMemoryProjectsRepository() {
    this.counter = new AtomicInteger(0);

    this.data = new ConcurrentHashMap<>();
  }

  public static InMemoryProjectsRepository getInstance() {
    return INSTANCE;
  }

  @Override
  public Project add(Project project) {
    data.put(project.getProjectId(), project);
    return project;
  }

  @Override
  public Project update(Project project) {
    data.put(project.getProjectId(), project);
    return data.get(project.getProjectId());
  }

  @Override
  public ProjectId nextId() {
    return ProjectId.of(counter.incrementAndGet());
  }

  @Override
  public List<Project> findAll() {
    return data.values().stream().collect(Collectors.toList());
  }


  @Override
  public Project findById(ProjectId projectId) {
    final Project project = data.get(projectId);
    if (project == null) {
      throw new NoSuchEntityException("No project for " + projectId.getValue());
    }
    return project;
  }

  @Override
  public List<Project> findByMemberId(MemberId memberId) {
    return data.values().stream().filter(project -> project.getParticipants().contains(memberId))
               .collect(Collectors.toList());
  }

}
