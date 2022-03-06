package org.esgi.use_cases.projects.infrastructure;

import org.esgi.shared_kernel.exceptions.NoSuchEntityException;
import org.esgi.use_cases.projects.domain.Project;
import org.esgi.use_cases.projects.domain.ProjectId;
import org.esgi.use_cases.projects.domain.ProjectsRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryProjectsRepository implements ProjectsRepository {
    private final AtomicInteger           counter;
    private final Map<ProjectId, Project> data;

    private static final InMemoryProjectsRepository INSTANCE = new InMemoryProjectsRepository();

    public static InMemoryProjectsRepository getInstance() {
        return INSTANCE;
    }

    public InMemoryProjectsRepository() {
        this.counter = new AtomicInteger(0);

        this.data = new ConcurrentHashMap<>();
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

}
