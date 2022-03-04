package org.esgi.use_cases.project.infrastructure;

import org.esgi.use_cases.project.domain.Project;
import org.esgi.use_cases.project.domain.ProjectId;
import org.esgi.use_cases.project.domain.Projects;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryProjects implements Projects {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<ProjectId, Project> data = new ConcurrentHashMap<>();

    @Override
    public int nextIdentity() {
        return 0;
    }

    @Override
    public Project findById(ProjectId id) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public Void add(Project Project) {
        return null;
    }

    @Override
    public Void removeById(ProjectId id) {
        return null;
    }
}
