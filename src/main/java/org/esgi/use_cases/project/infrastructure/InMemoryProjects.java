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
    public ProjectId nextIdentity() {
        ProjectId id = ProjectId.of(this.count.incrementAndGet());
        return id;
    }

    @Override
    public Project findById(ProjectId id) {
        final Project project = data.get(id);
        if(project == null) {
            throw new IllegalArgumentException("bad id");
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public ProjectId add(Project Project) {
        return null;
    }

    @Override
    public Void removeById(ProjectId id) {
        return null;
    }
}
